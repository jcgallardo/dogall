package es.jcgallardo.dogall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.jcgallardo.dogall.dog.DogsActivity;

public class WhoActivity extends Activity {
    Button b_seguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        b_seguir = (Button) findViewById(R.id.b_seguir);

        b_seguir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(WhoActivity.this,DogsActivity.class);
                startActivity(siguiente);
            }
        });
    }
}
