package com.gui;

import ws.LoginRequest;
import ws.LoginRequest.LoginListener;
import ws.ServerRequest.ServerRequestListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tocomfome.R;

public class LoginActivity extends Activity implements ServerRequestListener{

	EditText usuario;
	EditText senha;
	Button logar;
	protected static final String URL = "";//TODO : URL do webservice de login
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_intent);
		
		usuario = (EditText) findViewById(R.id.edtUsuarioLogin);
		senha = (EditText) findViewById(R.id.edtSenhaLogin);
		logar = (Button) findViewById(R.id.btnCadastrar);
		logar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				logar();
			}
		});
	}
	
	private void callUserIntent() {
		Intent i = new Intent(LoginActivity.this, InfoUsuarioActivity.class);
		startActivity(i);
	}
	
	private void logar() {
		String user = usuario.getText().toString();
		String pwd = senha.getText().toString();
		if (!user.isEmpty()
				&& !pwd.isEmpty()){
			LoginListener loginListener = new LoginListener()
			{
				@Override
				public void sucess(String token)
				{
					callUserIntent();
				}
				
				@Override
				public void error(String error)
				{
					Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
				}
			};
			
			LoginRequest.login(this, loginListener, user, pwd);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_intent, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onRequestStart() {
	}

	@Override
	public void onRequestComplete(String response, boolean error) {
	}
}
