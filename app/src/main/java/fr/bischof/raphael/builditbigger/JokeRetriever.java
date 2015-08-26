package fr.bischof.raphael.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import fr.bischof.raphael.jokeApi.JokeApi;

/**
 * Created by biche on 25/08/2015.
 */
public class JokeRetriever extends AsyncTask<Void, Void, String> {
    private static JokeApi myApiService = null;
    private OnJokeLoadedListener listener;

    public void setOnJokeLoadedListener(OnJokeLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(false);
                        }
                    });
            /*JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketeller-1048.appspot.com/_ah/api/");*/

            myApiService = builder.build();
        }

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
