package ws.requests;

import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

public class LoginRequest extends DefaultRequest {
	final String LOGIN_URL = SERVER_URL + "usuarios/login";

	private LoginRequest(Context context, ServerListener listener) {
		super(context, listener);
	}

	public void doLogin(String nome, String senha) {
		try {
			ServerRequest loginTask = new ServerRequest(context, LOGIN_URL, this, false);
			loginTask.execute(new BasicNameValuePair("nome", nome), new BasicNameValuePair("senha", senha));
		} catch (Exception ex) {
			listener.error(ex.getMessage());
		}
	}

	public static void login(Context context, ServerListener listener,
			String username, String password) {
		LoginRequest lr = new LoginRequest(context, listener);
		lr.doLogin(username, password);
	}
}
