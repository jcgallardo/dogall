package es.jcgallardo.dogall.dogdetail;


import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import es.jcgallardo.dogall.Dog;
import es.jcgallardo.dogall.DogDbHelper;
import es.jcgallardo.dogall.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DogDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogDetailFragment extends Fragment {
    private String mDogId;

    private CollapsingToolbarLayout mCollapsingView;
    private TextView mDogName;
    private TextView mDogDescription;
    private TextView mDogWeight;
    private TextView mDogHheight;
    private TextView mDogLifeexpectancy;
    private TextView mDogOthernames;
    private TextView mDogTemperament;
    private ImageView mDogPhoto;
    private TextView mDogFound;

    private DogDbHelper mDogDbHelper;


    public DogDetailFragment() {
        // Required empty public constructor
    }

    public static DogDetailFragment newInstance(String dogId) {
        DogDetailFragment fragment = new DogDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DOG_ID, dogId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDogId = getArguments().getString(ARG_DOG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dog_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mDogPhoto = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mDogName = (TextView) root.findViewById(R.id.tv_name);
        mDogDescription = (TextView) root.findViewById(R.id.tv_description);

        mDogDbHelper = new DogDbHelper(getActivity());

        loadDog();

        return root;
    }

    private void loadDog() {
        new GetDogByIdTask().execute();
    }

    private void showDog(Dog dog) {
        mCollapsingView.setTitle(dog.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + dog.getPhoto()))
                .centerCrop()
                .into(mDogPhoto);
        mDogDescription.setText(dog.getDescription());
        mDogName.setText(dog.getName());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private class GetDogByIdTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mDogDbHelper.getDogById(mDogId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showDog(new Dog(cursor));
            } else {
                showLoadError();
            }
        }
    }
}
