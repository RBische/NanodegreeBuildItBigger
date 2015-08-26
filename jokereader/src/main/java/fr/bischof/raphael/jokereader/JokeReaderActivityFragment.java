package fr.bischof.raphael.jokereader;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeReaderActivityFragment extends Fragment {

    public JokeReaderActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_joke_reader, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity().getIntent().hasExtra(JokeReaderActivity.EXTRA_JOKE)){
            ((TextView)view.findViewById(R.id.tvJoke)).setText(getActivity().getIntent().getStringExtra(JokeReaderActivity.EXTRA_JOKE));
        }
    }
}
