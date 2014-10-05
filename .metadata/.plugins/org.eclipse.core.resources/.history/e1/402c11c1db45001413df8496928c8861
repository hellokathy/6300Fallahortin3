package edu.gatech.seclass.unitconvertor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AreaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);
	}
	public void menuLaunch(View view){
		Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
	}
    public void handleFeetClick(View view){
		EditText txt = (EditText) findViewById(R.id.editText1);
		double meters = Double.parseDouble(txt.getText().toString());
        double feet = meters*3.28084; //conversion here
        txt.setText( String.valueOf(feet));
	}
    public void handleMetersClick(View view){
		EditText txt = (EditText) findViewById(R.id.editText1);
		double feet = Double.parseDouble(txt.getText().toString());
        double meters = feet/3.28084; //conversion here
        txt.setText( String.valueOf(meters););
	}
}
