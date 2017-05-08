package es.jcgallardo.dogall.dog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import es.jcgallardo.dogall.R;

public class DogsActivity extends AppCompatActivity {

    public static final String EXTRA_LAWYER_ID = "extra_dog_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DogsFragment fragment = (DogsFragment)
                getSupportFragmentManager().findFragmentById(R.id.dogs_container);

        if (fragment == null) {
            fragment = DogsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.dogs_container, fragment)
                    .commit();
        }
    }

}
