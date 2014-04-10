/*
 * Copyright (C) 2011 Sergey Margaritov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

  /* Implemented ColorPicker in a Simple Activity, instead of PreferenceActivity.
   * Complete credit goes to the author. 
   * I just made changes to show ColorPicker in a Activity.  */
package net.margaritov.activity.colorpicker;

import net.margaritov.R;
import net.margaritov.preference.colorpicker.AlphaPatternDrawable;
import net.margaritov.preference.colorpicker.ColorPickerDialog;
import net.margaritov.preference.colorpicker.ColorPickerPanelView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
//Test to show ColorPicker in a Activity.
public class TestColorPickerActivity extends Activity implements ColorPickerDialog.OnColorChangedListener{
    /** Called when the activity is first created. */
	
	View mView;
	ColorPickerDialog mDialog;
	private int mValue;
	private int rgbColor = Color.BLACK, argbColor = Color.BLACK;//
	private float mDensity = 0;
	private boolean mAlphaSliderEnabled = false;
	private boolean mHexValueEnabled = false;
	private boolean isClickedPickColor = false;
	
	private Button btnPickColor;// to display RGB color
	private ColorPickerPanelView panelShowAlphaSlider;//to dispplay Alpha color selected
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_colorpicker_activity);
        init(this);
    }
	
	private void init(Context context) {
		mDensity = context.getResources().getDisplayMetrics().density;

		btnPickColor=(Button)findViewById(R.id.panelPickColor);
		panelShowAlphaSlider=(ColorPickerPanelView)findViewById(R.id.panelShowAlphaSlider);
		
		btnPickColor.setOnClickListener(new OnClickListener() {//No alpha slider
			@Override
			public void onClick(View arg0) {
				isClickedPickColor=true;
				mAlphaSliderEnabled=false;
				mValue=rgbColor;
				setPreviewColor();
				displayStandaloneColorPick();
			}
		});
		
		panelShowAlphaSlider.setOnClickListener(new OnClickListener() {//Show alpha slider
			@Override
			public void onClick(View arg0) {
				isClickedPickColor=false;
				mAlphaSliderEnabled=true;
				mValue=argbColor;
				setPreviewColor();
				displayStandaloneColorPick();
			}
		});
		
	}

	@SuppressWarnings("deprecation")
	private void setPreviewColor() {
		if (mView == null) return;
		ImageView iView = new ImageView(this);
		LinearLayout widgetFrameView = ((LinearLayout)mView.findViewById(android.R.id.widget_frame));
		if (widgetFrameView == null) return;
		widgetFrameView.setVisibility(View.VISIBLE);
		widgetFrameView.setPadding(
			widgetFrameView.getPaddingLeft(),
			widgetFrameView.getPaddingTop(),
			(int)(mDensity * 8),
			widgetFrameView.getPaddingBottom()
		);
		// remove already create preview image
		int count = widgetFrameView.getChildCount();
		if (count > 0) {
			widgetFrameView.removeViews(0, count);
		}
		widgetFrameView.addView(iView);
		widgetFrameView.setMinimumWidth(0);
		iView.setBackgroundDrawable(new AlphaPatternDrawable((int)(5 * mDensity)));
		iView.setImageBitmap(getPreviewBitmap());
	}
	

	private Bitmap getPreviewBitmap() {
		int d = (int) (mDensity * 31); //30dip
		int color = mValue;
		Bitmap bm = Bitmap.createBitmap(d, d, Config.ARGB_8888);
		int w = bm.getWidth();
		int h = bm.getHeight();
		int c = color;
		for (int i = 0; i < w; i++) {
			for (int j = i; j < h; j++) {
				c = (i <= 1 || j <= 1 || i >= w-2 || j >= h-2) ? Color.GRAY : color;
				bm.setPixel(i, j, c);
				if (i != j) {
					bm.setPixel(j, i, c);
				}
			}
		}

		return bm;
	}
	
	@Override
	public void onColorChanged(int color) {
		mValue=color;
		if(isClickedPickColor){
			rgbColor = color;
			btnPickColor.setBackgroundColor(color);	
		}else{
			argbColor=color;
			panelShowAlphaSlider.setColor(color);
		}
	}
	
	public boolean displayStandaloneColorPick() {
		showDialog(null);
		return false;
	}
	
	protected void showDialog(Bundle state) {
		mDialog = new ColorPickerDialog(this, mValue);
		mDialog.setOnColorChangedListener(this);
		if (mAlphaSliderEnabled) {
			mDialog.setAlphaSliderVisible(true);
		}
		if (mHexValueEnabled) {
			mDialog.setHexValueEnabled(true);
		}
		if (state != null) {
			mDialog.onRestoreInstanceState(state);
		}
		mDialog.show();
	}

	/**
	 * Toggle Alpha Slider visibility (by default it's disabled)
	 * @param enable
	 */
	public void setAlphaSliderEnabled(boolean enable) {
		mAlphaSliderEnabled = enable;
	}

	/**
	 * Toggle Hex Value visibility (by default it's disabled)
	 * @param enable
	 */
	public void setHexValueEnabled(boolean enable) {
		mHexValueEnabled = enable;
	}

	/**
	 * For custom purposes. Not used by ColorPickerPreferrence
	 * @param color
	 * @author Unknown
	 */
    public static String convertToARGB(int color) {
        String alpha = Integer.toHexString(Color.alpha(color));
        String red = Integer.toHexString(Color.red(color));
        String green = Integer.toHexString(Color.green(color));
        String blue = Integer.toHexString(Color.blue(color));

        if (alpha.length() == 1) {
            alpha = "0" + alpha;
        }

        if (red.length() == 1) {
            red = "0" + red;
        }

        if (green.length() == 1) {
            green = "0" + green;
        }

        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        return "#" + alpha + red + green + blue;
    }
    
    /**
	 * For custom purposes. Not used by ColorPickerPreference
	 * @param color
	 * @author Charles Rosaaen
	 * @return A string representing the hex value of color,
	 * without the alpha value
	 */
    public static String convertToRGB(int color) {
        String red = Integer.toHexString(Color.red(color));
        String green = Integer.toHexString(Color.green(color));
        String blue = Integer.toHexString(Color.blue(color));

        if (red.length() == 1) {
            red = "0" + red;
        }

        if (green.length() == 1) {
            green = "0" + green;
        }

        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        return "#" + red + green + blue;
    }

    /**
     * For custom purposes. Not used by ColorPickerPreferrence
     * @param argb
     * @throws NumberFormatException
     * @author Unknown
     */
	public static int convertToColorInt(String argb) throws IllegalArgumentException {

		if (!argb.startsWith("#")) {
			argb = "#" + argb;
		}

		return Color.parseColor(argb);
	}
}