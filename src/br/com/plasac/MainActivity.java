package br.com.plasac;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String[] credenciados = { "","HSC", "Plasac" };
	ArrayAdapter<String> mCredenciados;
	Spinner spnCredenciados;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		mCredenciados = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, credenciados);
		spnCredenciados = (Spinner) findViewById(R.id.spinner1);
		spnCredenciados.setAdapter(mCredenciados);

	}
}
