package com.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastrarReceitaActivty extends Activity {
	
	private static final int RANDOMCODE = 455;
	private static final String COUNTPARAMS = "countParams";
	ImageView imagem;
	EditText nome;
	EditText porcoes;
	EditText tempoPreparo;
	Button btnIngredientes;
	Button btnPassos;
	List<String> ingredientes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar);

		imagem = (ImageView) findViewById(R.id.imgReceita);
		nome = (EditText) findViewById(R.id.edtIngrediente);
		porcoes = (EditText) findViewById(R.id.edtPorcoesReceita);
		tempoPreparo = (EditText) findViewById(R.id.edtTempoPreparoReceita);
		ingredientes = new ArrayList<>();
		
		btnIngredientes = (Button) findViewById(R.id.btnAddIngredientes);
		btnIngredientes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cadastroIngrediente();
			}
		});
	}

	private void cadastroIngrediente() {
		Intent intent = new Intent(CadastrarReceitaActivty.this, CadastrarListaIngredientesActivty.class);
		intent.putExtra(COUNTPARAMS, ingredientes.size());
		for(int i = 0; i < ingredientes.size(); i++){
			intent.putExtra(String.valueOf(i), ingredientes.get(i));
		}
		startActivityForResult(intent, RANDOMCODE);
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
		super.onActivityResult(requestCode, resultCode, data); 
		switch(requestCode) { 
		case (RANDOMCODE) : 
			if (resultCode == Activity.RESULT_OK) { 
				int count = Integer.parseInt(data.getExtras().getString(COUNTPARAMS));
				for(int i = 0; i < count; i++){
					data.getExtras().getString(String.valueOf(i));
				} 
			} 
			break; 
		} 
	}
}
