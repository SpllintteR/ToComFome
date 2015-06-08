package com.gui;

import org.json.JSONException;
import org.json.JSONObject;

import ws.requests.LoginRequest;
import ws.requests.ServerListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.furb.receitas.bean.UsuarioBean;

public class LoginActivity extends Activity{

	EditText usuario;
	EditText senha;
	Button logar;
	protected static final String URL = "";// TODO : URL do webservice de login

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_intent);

		usuario = (EditText) findViewById(R.id.edtUsuarioLogin);
		senha = (EditText) findViewById(R.id.edtSenhaLogin);
		logar = (Button) findViewById(R.id.btnLogar);
		logar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				logar();
			}
		});
	}

	private void logar() {
		String user = usuario.getText().toString();
		String pwd = senha.getText().toString();
		if (!user.isEmpty() && !pwd.isEmpty()) {
			ServerListener loginListener = new ServerListener() {
				@Override
				public void sucess(JSONObject obj) {
					UsuarioBean usuario = new UsuarioBean();
					try {
						usuario.setNome(obj.getString("nome"));
						usuario.setEmail(obj.getString("email"));
						usuario.setSenha(obj.getString("senha"));
						usuario.setOID(Integer.parseInt(obj.getString("oid")));
						//TODO: setar esse usuário pra algo global
						LoginActivity.this.finish();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void error(String error) {
					Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG)
							.show();
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
}
