package develop.beta1139.investigateproject;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tomo on 16/09/18.
 */
public class JsonLoader extends AsyncTaskLoader<JSONObject> {

    private static final int READ_BUFFER_SIZE = 1024;

    private String mUrl;

    public interface AsyncCallback {
        void postExecute(JSONObject result);
    }

    private AsyncCallback mAsyncCallback = null;

    public JsonLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public JSONObject loadInBackground() {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(mUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject jsonObject = new JSONObject(new String(sb.toString()));
            return jsonObject;

        } catch (MalformedURLException e) {
            // Do Nothing...
        } catch (ProtocolException e) {
            // Do Nothing...
        } catch (IOException e) {
            // Do Nothing...
        } catch (JSONException e) {
            // Do Nothing...
        }

        return null;
    }
}
