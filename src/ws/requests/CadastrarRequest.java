package ws.requests;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

public class CadastrarRequest extends DefaultRequest
{
	final String URL = SERVER_URL + "usuarios/incluir";
	
	private CadastrarRequest(Context context, ServerListener listener)
	{
		super(context, listener);
	}
	
	public void cadastrar(String username, String password, String email)
	{
		NameValuePair kpUsername = new BasicNameValuePair("nome", username);
		NameValuePair kpPassword = new BasicNameValuePair("senha", password);
		NameValuePair kpEmail = new BasicNameValuePair("email", email);
		try
		{
			ServerRequest task = new ServerRequest(context, URL, this, false);		
			task.execute(kpUsername, kpPassword, kpEmail);
		}
		catch (Exception ex)
		{
			listener.error(ex.getMessage());
		}
	}

	public static void cadastrar(Context context, ServerListener listener, String username, String password, String email)
	{
		CadastrarRequest lr = new CadastrarRequest(context, listener);
		lr.cadastrar(username, password, email);
	}
	
	
}
