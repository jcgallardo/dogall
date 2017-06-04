package es.jcgallardo.dogall.dog;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.File;

import es.jcgallardo.dogall.R;
import es.jcgallardo.dogall.ResultCameraActivity;

public class DogsActivity extends AppCompatActivity {

    public static final String EXTRA_DOG_ID = "extra_dog_id";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    // reconocimiento de imágenes
    public static final String FILE_NAME = "temp.jpg";
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

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
                startCamera();
            }
        });

    }

    private void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
             Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
             System.out.println("Imagen capturada: " + photoUri);
             Intent act_siguiente = new Intent(DogsActivity.this,ResultCameraActivity.class);
             act_siguiente.putExtra("photoUri", photoUri.toString());
             startActivity(act_siguiente);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
        }
    }



}
