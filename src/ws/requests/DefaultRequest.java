package ws.requests;

import org.json.JSONObject;

import ws.requests.ServerRequest.ServerRequestListener;
import android.app.ProgressDialog;
import android.content.Context;

public class DefaultRequest implements ServerRequestListener {

	Context context;
	ProgressDialog progress;
	ServerListener listener;
	String SERVER_URL = "http://receitas-rs.elasticbeanstalk.com/rs/";

	public DefaultRequest(Context context, ServerListener listener) {
		this.listener = listener;
		this.context = context;
		this.progress = new ProgressDialog(context);
		this.progress.setIndeterminate(true);
		this.progress.setCancelable(false);
		this.progress.setTitle("Aguarde");
		this.progress.setMessage("Processando...");
	}

	@Override
	public void onRequestStart() {
		progress.show();
	}

	@Override
	public void onRequestComplete(String response, boolean error) {
		progress.dismiss();

		if (!error) {
			try {
				JSONObject json = new JSONObject(response);

				if ((!error) && (json.has("error")))
					throw new Exception(json.getString("error"));

				listener.sucess(json);
			} catch (Exception ex) {
				error = true;
				response = ex.getMessage();
			}
		}

		if (error)
			listener.error(response);
	}

}
