package com.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CadastrarListaIngredientesActivty extends Activity {
	
	private static final int RANDOMCODE = 455;
	private static final String COUNTPARAMS = "countParams";
	Button ok;
	Button add;
	ListView listView;
	ArrayAdapter<String> adapter;
	EditText ingrediente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_ingrediente);
		
		listView = (ListView) findViewById(R.id.lstIngredientes);
		adapter = new ArrayAdapter<>(this, 4312);
		listView.setAdapter(adapter);
		add = (Button) findViewById(R.id.btnAddIngredientes);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				add();
			}
		});
		ok = (Button) findViewById(R.id.btnEncerrar);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				done();
			}
		});
		ingrediente = (EditText) findViewById(R.id.edtIngrediente);
	}

	protected void add() {
		adapter.add(ingrediente.getText().toString());
		ingrediente.getText().clear();
	}

	private void done() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra(COUNTPARAMS, adapter.getCount());
		for(int i = 0; i < adapter.getCount(); i++){
			resultIntent.putExtra(String.valueOf(i), adapter.getItem(i));
		}
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
}
