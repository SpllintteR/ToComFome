package ws.requests;

import android.content.Context;

public class ExibirReceitasRequest extends DefaultRequest {

	final String URL = SERVER_URL + "especiarias/lista-todas";

	private ExibirReceitasRequest(Context context, ServerListener listener) {
		super(context, listener);
	}

	public void execute() {
		try {
			ServerRequest loginTask = new ServerRequest(context, URL, this, true);
			loginTask.execute();
		} catch (Exception ex) {
			listener.error(ex.getMessage());
		}
	}

	public static void execute(Context context, ServerListener listener) {
		ExibirReceitasRequest req = new ExibirReceitasRequest(context, listener);
		req.execute();
	}
}