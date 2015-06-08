package ws.requests;

import org.json.JSONObject;

public interface ServerListener {

	void sucess(JSONObject json);

	void error(String error);

}
