package com.gui;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import ws.ServerRequest;
import ws.ServerRequest.ServerRequestListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tocomfome.R;

public class CadastrarActivity extends Activity implements ServerRequestListener{

	Button btnCadastrar;
	EditText edtUsuario;
	EditText edtSenha;
	EditText edtEmail;
	protected static final String URL = "";//TODO

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar);

		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		edtEmail = (EditText) findViewById(R.id.edtEmail);

		btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
		btnCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String user = edtUsuario.getText().toString();
				String senha = edtSenha.getText().toString();
				String email = edtEmail.getText().toString();
				if (!user.isEmpty()
						&& !senha.isEmpty()
						&& !email.isEmpty()) {
					ServerRequest ws = new ServerRequest(CadastrarActivity.this, URL, CadastrarActivity.this);
					ws.execute(new BasicNameValuePair("user", user), new BasicNameValuePair("senha", senha), new BasicNameValuePair("email", email));
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onRequestStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestComplete(String response, boolean error) {
		try {
			JSONObject json = new JSONObject(response);
			Toast.makeText(this, json.toString(), Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
