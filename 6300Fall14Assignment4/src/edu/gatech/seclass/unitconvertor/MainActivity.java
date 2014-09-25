package edu.gatech.seclass.unitconvertor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void distanceLaunch(View view){
		setContentView(R.layout.activity_distance);
	}
	public void areaLaunch(View view){

		setContentView(R.layout.activity_area);
	}
	public void currencyLaunch(){
		setContentView(R.layout.activity_currency);
		
	}

}
