package es.jcgallardo.dogall;

import android.content.ContentValues;

import java.util.UUID;

/**
 * Created by jcgallardo on 08/05/2017.
 */

public class Dog {
    private String id;
    private String name;
    private String description;
    private String weight;
    private String height;
    private String lifeexpectancy;
    private String othernames;
    private String temperament;
    private String photo;
    private String found;

    public Dog(String name, String description, String weight, String height, String life_expectancy, String other_names, String temperament, String photo) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.lifeexpectancy = life_expectancy;
        this.othernames = other_names;
        this.temperament = temperament;
        this.photo = photo;
        this.found = "0";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public String getWeight() {
        return weight;
    }


    public String getHeight() {
        return height;
    }


    public String getLifeexpectancy() {
        return lifeexpectancy;
    }


    public String getOthernames() {
        return othernames;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getPhoto() {
        return photo;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(DogContract.RazaPerroEntry.ID, id);
        values.put(DogContract.RazaPerroEntry.NAME, name);
        values.put(DogContract.RazaPerroEntry.DESCRIPTION, description);
        values.put(DogContract.RazaPerroEntry.WEIGHT, weight);
        values.put(DogContract.RazaPerroEntry.HEIGHT, weight);
        values.put(DogContract.RazaPerroEntry.LIFEEXPECTANCY, lifeexpectancy);
        values.put(DogContract.RazaPerroEntry.OTHERNAMES, othernames);
        values.put(DogContract.RazaPerroEntry.TEMPERAMENT, temperament);
        values.put(DogContract.RazaPerroEntry.PHOTO, photo);
        values.put(DogContract.RazaPerroEntry.FOUND, found);
        return values;
    }

}
