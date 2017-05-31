package es.jcgallardo.dogall.dog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import es.jcgallardo.dogall.R;

public class DogsActivity extends AppCompatActivity {

    public static final String EXTRA_DOG_ID = "extra_dog_id";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

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

        // Damos acción al icono de camara
        FloatingActionButton camera = (FloatingActionButton) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            System.out.println("******************************************* Se capturó **********************************************");
            // Procesamos la foto
            /*
            ImageView mImageView = (ImageView) findViewById(R.id.id_foto);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            System.out.println(mImageView.toString());
            */
        }
    }



}
