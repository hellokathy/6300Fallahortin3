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
}
