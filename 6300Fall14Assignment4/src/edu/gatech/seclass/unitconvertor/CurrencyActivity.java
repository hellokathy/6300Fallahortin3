package edu.gatech.seclass.unitconvertor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CurrencyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_currency);
	}
	public void menuLaunch(View view){
		Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
	}
    
    public void handleDollarsClick(View view){
		EditText txt = (EditText) findViewById(R.id.editText1);
		double euros = Double.parseDouble(txt.getText().toString());
        double dollars = euros*1.27; //conversion here
        txt.setText( String.valueOf(dollars););
	}
    public void handleEurosClick(View view){
		EditText txt = (EditText) findViewById(R.id.editText1);
		double dollars = Double.parseDouble(txt.getText().toString());
        double euros = dollars/1.27; //conversion here
        txt.setText(milesToKm( String.valueOf(euros););
	}
}
