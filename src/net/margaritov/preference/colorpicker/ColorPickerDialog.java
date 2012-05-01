/*
 * Copyright (C) 2010 Daniel Nilsson
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

package net.margaritov.preference.colorpicker;

import net.margaritov.preference.colorpicker.R;

import android.R.color;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColorPickerDialog 
	extends 
		Dialog 
	implements
		ColorPickerView.OnColorChangedListener,
		View.OnClickListener, TextWatcher {

	private ColorPickerView mColorPicker;

	private ColorPickerPanelView mOldColor;
	private ColorPickerPanelView mNewColor;

	private TextView mColorTextEditLabel;
  private EditText mRedEditText;
  private EditText mGreenEditText;
  private EditText mBlueEditText;
  private EditText mAlphaEditText;

  private OnColorChangedListener mListener;

	public interface OnColorChangedListener {
		public void onColorChanged(int color);
	}
	
	public ColorPickerDialog(Context context, int initialColor) {
		super(context);

		init(initialColor);
	}

	private void init(int color) {
		// To fight color branding.
		getWindow().setFormat(PixelFormat.RGBA_8888);

		setUp(color);

	}

	private void setUp(int color) {
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View layout = inflater.inflate(R.layout.dialog_color_picker, null);

		setContentView(layout);

		setTitle(R.string.dialog_color_picker);
		
		mColorPicker = (ColorPickerView) layout.findViewById(R.id.color_picker_view);
		mOldColor = (ColorPickerPanelView) layout.findViewById(R.id.old_color_panel);
		mNewColor = (ColorPickerPanelView) layout.findViewById(R.id.new_color_panel);
		mColorTextEditLabel = (TextView) layout.findViewById(R.id.color_text_edit_label);
    mRedEditText = (EditText) layout.findViewById(R.id.red_edit_text);
    mGreenEditText = (EditText) layout.findViewById(R.id.green_edit_text);
    mBlueEditText = (EditText) layout.findViewById(R.id.blue_edit_text);
    mAlphaEditText = (EditText) layout.findViewById(R.id.alpha_edit_text);
		
		((LinearLayout) mOldColor.getParent()).setPadding(
			Math.round(mColorPicker.getDrawingOffset()), 
			0, 
			Math.round(mColorPicker.getDrawingOffset()), 
			0
		);	
		
		mOldColor.setOnClickListener(this);
		mNewColor.setOnClickListener(this);
		mColorPicker.setOnColorChangedListener(this);
		mOldColor.setColor(color);
		mColorPicker.setColor(color, true);
		
		mAlphaEditText.addTextChangedListener(this);
    mRedEditText.addTextChangedListener(this);
    mGreenEditText.addTextChangedListener(this);
    mBlueEditText.addTextChangedListener(this);
    onColorChanged(color);

	}

	@Override
	public void onColorChanged(int color) {

		mNewColor.setColor(color);
    mAlphaEditText.setText(String.valueOf((color>>24)&0xFF));
    mRedEditText.setText(String.valueOf((color>>16)&0xFF));
    mGreenEditText.setText(String.valueOf((color>>8)&0xFF));
    mBlueEditText.setText(String.valueOf((color>>0)&0xFF));

		/*
		if (mListener != null) {
			mListener.onColorChanged(color);
		}
		*/

	}

	public void setAlphaSliderVisible(boolean visible) {
		mColorPicker.setAlphaSliderVisible(visible);
		if (visible) {
		  mAlphaEditText.setVisibility(EditText.VISIBLE);
		  mColorTextEditLabel.setText(R.string.color_text_edit_rgba);
		} else {
      mAlphaEditText.setVisibility(EditText.INVISIBLE);
      mColorTextEditLabel.setText(R.string.color_text_edit_rgb);
		}
	}
	
	/**
	 * Set a OnColorChangedListener to get notified when the color
	 * selected by the user has changed.
	 * @param listener
	 */
	public void setOnColorChangedListener(OnColorChangedListener listener){
		mListener = listener;
	}

	public int getColor() {
		return mColorPicker.getColor();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.new_color_panel) {
			if (mListener != null) {
				mListener.onColorChanged(mNewColor.getColor());
			}
		}
		dismiss();
	}

	int updateColorComponent(int pos, String value, int color) {
	  int valueNumber;
    if (value.equals("")) {
      valueNumber = 0;
    } else {
      valueNumber = Integer.valueOf(value);
    }
    if (valueNumber>255)
      valueNumber=255;
    if (valueNumber<0)
      valueNumber=0;
    int mask = 0xFF << pos;
    mask = ~mask;
    color = color & mask;
    color = color | (valueNumber<<pos);
    return color;
	}
	
  @Override
  public void afterTextChanged(Editable s) {
    int color = getColor();
    color = updateColorComponent(24, mAlphaEditText.getText().toString(), color);
    color = updateColorComponent(16, mRedEditText.getText().toString(), color);
    color = updateColorComponent(8, mGreenEditText.getText().toString(), color);
    color = updateColorComponent(0, mBlueEditText.getText().toString(), color);
    mNewColor.setColor(color);
    mColorPicker.setColor(color);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }
	
}
