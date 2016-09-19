package develop.beta1139.investigateproject;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tomo on 16/09/19.
 */
public class JsonLoaderManager implements LoaderManager.LoaderCallbacks<JSONObject> {

    private Activity mActivity;
    private String mUrl;

    public JsonLoaderManager(Activity activity, String url) {
        mActivity = activity;
        mUrl = url;
    }

    @Override
    public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
        JsonLoader jsonLoader = new JsonLoader(mActivity, mUrl);
        jsonLoader.forceLoad();
        return jsonLoader;
    }

    @Override
    public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
        if (data != null) {
            try {
                JSONObject jsonObject = data.getJSONObject("request");
                String str = jsonObject.getString("url");

                if (!str.equals("")) {
                    TextView textView = (TextView) mActivity.findViewById(R.id.jsonTextView);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(str);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONObject> loader) {
        // Do Nothing...
    }
}
