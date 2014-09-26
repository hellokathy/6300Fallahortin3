package edu.gatech.seclass.unitconvertor;

import android.app.Activity;
import android.content.Intent;
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
		Intent intent = new Intent(view.getContext(), DistanceActivity.class);
        startActivity(intent);
	}
	public void areaLaunch(View view){

		Intent intent = new Intent(view.getContext(), AreaActivity.class);
        startActivity(intent);
	}
	public void currencyLaunch(View view){
		Intent intent = new Intent(view.getContext(), CurrencyActivity.class);
        startActivity(intent);
	}

}
