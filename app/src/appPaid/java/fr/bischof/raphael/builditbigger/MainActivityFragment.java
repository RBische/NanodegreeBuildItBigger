package fr.bischof.raphael.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.bischof.raphael.jokereader.JokeReaderActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Bind(R.id.btnGCE)
    Button mBtnGCE;
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
        mBtnGCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JokeRetriever retriever = new JokeRetriever();
                retriever.setOnJokeLoadedListener(new JokeRetriever.OnJokeLoadedListener() {
                    @Override
                    public void onJokeLoaded(String joke) {
                        Intent intent = new Intent(getActivity(),JokeReaderActivity.class);
                        intent.putExtra(JokeReaderActivity.EXTRA_JOKE,joke);
                        startActivity(intent);
                    }
                });
                retriever.execute(getActivity());
            }
        });
    }
}
