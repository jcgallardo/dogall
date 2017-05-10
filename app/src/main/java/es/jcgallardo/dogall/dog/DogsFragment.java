package es.jcgallardo.dogall.dog;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.jcgallardo.dogall.DogContract;
import es.jcgallardo.dogall.DogDbHelper;
import es.jcgallardo.dogall.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogsFragment extends Fragment {
    private DogDbHelper mDogHelper;

    private ListView mDogList;
    private DogCursorAdapter mDogAdapter;
    private FloatingActionButton mAddButton;

    public DogsFragment() {
        // Required empty public constructor
    }
    public static DogsFragment newInstance() {
        return new DogsFragment();
    }

    private class DogLoadTask extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mDogHelper.getAllRazaPerros();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mDogAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_dogs, container, false);

        // referencias UI
        mDogList = (ListView) root.findViewById(R.id.dogs_list);
        mDogAdapter = new DogCursorAdapter(getActivity(),null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mDogList.setAdapter(mDogAdapter);

        // Instancia de helper
        mDogHelper = new DogDbHelper(getActivity());

        // Carga de datos
        loadDogs();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

    }

    private void loadDogs() {
        // Cargar datos...
        new DogLoadTask().execute();
    }

}
