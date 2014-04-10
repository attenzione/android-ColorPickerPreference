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
package net.margaritov.testcolorpicker;

import net.margaritov.R;
import net.margaritov.activity.colorpicker.TestColorPickerActivity;
import net.margaritov.preference.colorpicker.TestColorPickerPreferenceActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Test extends Activity {
    /** Called when the activity is first created. */
	
	private Button btnStandalone,btnPreference;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
    	btnStandalone=(Button)findViewById(R.id.btnStandalone);
		btnPreference=(Button)findViewById(R.id.btnPreference);
		
        btnStandalone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(Test.this,TestColorPickerActivity.class);
				startActivity(intent);
			}
		});
		
		btnPreference.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(Test.this,TestColorPickerPreferenceActivity.class);
				startActivity(intent);
			}
		});
    }
	
}