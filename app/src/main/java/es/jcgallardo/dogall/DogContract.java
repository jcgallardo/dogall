package es.jcgallardo.dogall;

import android.provider.BaseColumns;

/**
 * Created by jcgallardo on 08/05/2017.
 */

public class DogContract {
    public static abstract class RazaPerroEntry implements BaseColumns {
        public static final String TABLE_NAME = "es/jcgallardo/dogall/dogs";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String WEIGHT = "weight";
        public static final String HEIGHT = "height";
        public static final String LEGS = "legs";
        public static final String LIFEEXPECTANCY = "lifeexpectancy";
        public static final String OTHERNAMES = "othernames";
        public static final String TEMPERAMENT = "temperament";
        public static final String PHOTO = "photo";
        public static final String FOUND = "found";
    }
}
