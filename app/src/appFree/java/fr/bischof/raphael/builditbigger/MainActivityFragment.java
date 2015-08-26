package fr.bischof.raphael.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.bischof.raphael.jokereader.JokeReaderActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private String joke;
    @Bind(R.id.btnGCE)
    Button mBtnGCE;
    private InterstitialAd mInterstitialAd;
    private ProgressDialog progress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                showJoke();
            }
        });

        requestNewInterstitial();
        mBtnGCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress==null){
                    progress = new ProgressDialog(getActivity());
                }
                progress.setMessage(getString(R.string.loading_joke));
                progress.show();
                JokeRetriever retriever = new JokeRetriever();
                retriever.setOnJokeLoadedListener(new JokeRetriever.OnJokeLoadedListener() {
                    @Override
                    public void onJokeLoaded(String joke) {
                        if (getActivity()!=null){
                            progress.dismiss();
                            progress = null;
                            MainActivityFragment.this.joke = joke;
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                showJoke();
                            }
                        }
                    }
                });
                retriever.execute(getActivity());
            }
        });
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void showJoke(){
        Intent intent = new Intent(getActivity(),JokeReaderActivity.class);
        intent.putExtra(JokeReaderActivity.EXTRA_JOKE,joke);
        startActivity(intent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
