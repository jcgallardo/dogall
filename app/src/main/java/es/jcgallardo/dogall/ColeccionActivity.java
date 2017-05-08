package es.jcgallardo.dogall;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Connection;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColeccionActivity extends AppCompatActivity {
    private ListView listDogs;
    DogDbHelper dogDB;
    List<String> item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleccion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // my code
        listDogs = (ListView) findViewById(R.id.dogs_list);

        //showDogs();
    }

    private void showDogs(){
        dogDB = new DogDbHelper(this);
        Cursor c = dogDB.getAllRazaPerros();
        item = new ArrayList<String>();
        String name = "", description = "";
        if(c.moveToFirst()){
            do{
                name = c.getString(2);
                description = c.getString(3);
                item.add(name + " " + description);
            }while(c.moveToNext());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, item);
        listDogs.setAdapter(adaptador);
    }

}
