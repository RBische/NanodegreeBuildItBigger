package fr.bischof.raphael.jokereader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JokeReaderActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "JokeExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_reader);
    }
}
