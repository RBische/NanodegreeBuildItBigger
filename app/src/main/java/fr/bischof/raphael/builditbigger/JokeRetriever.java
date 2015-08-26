package fr.bischof.raphael.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import fr.bischof.raphael.jokeApi.JokeApi;

/**
 * Created by biche on 25/08/2015.
 */
public class JokeRetriever extends AsyncTask<Context, Void, String> {
    private static JokeApi myApiService = null;
    private Context context;
    private OnJokeLoadedListener listener;

    public void setOnJokeLoadedListener(OnJokeLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketeller-1048.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener!=null){
            listener.onJokeLoaded(result);
        }
    }
    interface OnJokeLoadedListener{
        void onJokeLoaded(String joke);
    }
}
