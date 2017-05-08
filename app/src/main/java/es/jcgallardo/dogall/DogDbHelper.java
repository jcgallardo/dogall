package es.jcgallardo.dogall;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jcgallardo on 08/05/2017.
 */

public class DogDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Razaperros.db";

    public DogDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE
        db.execSQL("CREATE TABLE " + DogContract.RazaPerroEntry.TABLE_NAME + " ("
                + DogContract.RazaPerroEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DogContract.RazaPerroEntry.ID + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.NAME + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.DESCRIPTION + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.WEIGHT + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.HEIGHT + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.LIFEEXPECTANCY + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.OTHERNAMES + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.TEMPERAMENT + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.PHOTO + " TEXT NOT NULL,"
                + DogContract.RazaPerroEntry.FOUND + " TEXT default 0,"
                + "UNIQUE (" + DogContract.RazaPerroEntry.ID + "))");

        // INSERT VALUES
        mockData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Método para crear razas de perros de prueba
     * @param sqLiteDatabase
     */
    private void mockData(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Se han insertado nuevas razas");
        mockRazaPerro(sqLiteDatabase, new Dog("P-000001", "Yorkshire Terrier", "El Yorkshire terrier es una raza canina producto de la combinación de terrier escoceses e ingleses, que se originó cuando una parte de la población de Escocia se vio desplazada, debido a la Revolución industrial, y se asentaron en Inglaterra.","3.2Kg","Pequeño","13 - 16 años", "Yorkie","Audaz, Inteligente, Valiente, Confiado, Independiente","yorkshire.jpg"));
        mockRazaPerro(sqLiteDatabase, new Dog("P-000002","Pastor Alemán","El pastor alemán u ovejero alemán (en alemán: Deutscher Schäferhund) es una raza canina que proviene de Alemania.2 La raza es relativamente nueva, ya que su origen se remonta a 1899.3 Forman parte del grupo de pastoreo, debido a que fueron perros desarrollados originalmente para reunir y vigilar ovejas. Desde entonces, sin embargo, gracias a su fuerza, inteligencia,4 capacidad de entrenamiento y obediencia,4 los pastores alemanes de todo el mundo son a menudo la raza preferida para muchos otros tipos de trabajo, como son: perro guardián, guía de ciegos, animal de salvamento, perro policía y otros, según el uso que le den las fuerzas de seguridad y el ejército. En muchos países incluso cuentan con unidades específicas denominadas K-9.5","22 - 40Kg", "55 - 65 cm","9 - 13 años","","Obediente, Leal, Inteligente, Valiente, Vigilante, Curioso, Alerta, Confiado","pastoraleman.jpg"));
    }

    /**
     * Método para insertar en la base de datos la raza de perro
     * @param db
     * @param razap
     * @return
     */
    public long mockRazaPerro(SQLiteDatabase db, Dog razap) {
        return db.insert(
                DogContract.RazaPerroEntry.TABLE_NAME,
                null,
                razap.toContentValues());
    }

    /**
     * Método para obtener todas las razas de perros insertadas hasta el momento
     * @return
     */
    public Cursor getAllRazaPerros(){
        System.out.println("Get All Dogs");
        return getReadableDatabase()
                .query(
                        DogContract.RazaPerroEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    /**
     * Método para obtener una raza de perro identificada por un ID
     * @param razapId
     * @return
     */
    public Cursor getLawyerById(String razapId) {
        return getReadableDatabase().query(
                DogContract.RazaPerroEntry.TABLE_NAME,
                null,
                DogContract.RazaPerroEntry.ID + " LIKE ?",
                new String[]{razapId},
                null,
                null,
                null);
    }

}
