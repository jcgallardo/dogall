package es.jcgallardo.dogall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends Activity {
    Button boton_siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        boton_siguiente = (Button) findViewById(R.id.b_comenzar);

        boton_siguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(IndexActivity.this,WhoActivity.class);
                startActivity(siguiente);
            }
        });
    }
}
