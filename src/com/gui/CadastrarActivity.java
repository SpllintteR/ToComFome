package com.gui;

import org.json.JSONException;
import org.json.JSONObject;

import ws.requests.CadastrarRequest;
import ws.requests.ServerListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.furb.receitas.bean.UsuarioBean;

public class CadastrarActivity extends Activity {

	Button btnCadastrar;
	EditText edtUsuario;
	EditText edtSenha;
	EditText edtEmail;

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
				cadastrar();
			}
		});
	}

	private void cadastrar() {
		String user = edtUsuario.getText().toString();
		String senha = edtSenha.getText().toString();
		String email = edtEmail.getText().toString();
		if (!user.isEmpty() && !senha.isEmpty() && !email.isEmpty()) {
			ServerListener listener = new ServerListener() {
				@Override
				public void sucess(JSONObject obj) {
					Toast.makeText(CadastrarActivity.this,
							"Usuário Cadastrado com sucesso!",
							Toast.LENGTH_LONG).show();
					UsuarioBean usuario = new UsuarioBean();
					try {
						usuario.setNome(obj.getString("nome"));
						usuario.setEmail(obj.getString("email"));
						usuario.setSenha(obj.getString("senha"));
						usuario.setOID(Integer.parseInt(obj.getString("oid")));
						//TODO: setar esse usuário pra algo global
					} catch (JSONException e) {
						e.printStackTrace();
					}
					CadastrarActivity.this.finish();
				}

				@Override
				public void error(String error) {
					Toast.makeText(CadastrarActivity.this, "Erro ao cadastrar usuário: " + error,
							Toast.LENGTH_LONG).show();
				}
			};
			CadastrarRequest.cadastrar(this, listener, user, senha, email);
		}
	}
}
