package fr.bischof.raphael.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by biche on 25/08/2015.
 */
public class JokeRetrieverTest extends AndroidTestCase {
    public void testVerifyGCEJoke() {

        final CountDownLatch signal = new CountDownLatch(1);
        JokeRetriever retriever = new JokeRetriever();
        retriever.setOnJokeLoadedListener(new JokeRetriever.OnJokeLoadedListener() {
            @Override
            public void onJokeLoaded(String joke) {
                assertNotSame(joke, null);
                assertNotSame(joke,"");
                signal.countDown();// notify the count down latch
            }
        });
        retriever.execute(getContext());
        try {
            signal.await();// wait for callback
        } catch (InterruptedException e) {
            assertTrue(false);
        }
    }
}
