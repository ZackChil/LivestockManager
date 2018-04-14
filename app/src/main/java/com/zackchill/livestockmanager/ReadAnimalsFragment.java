package com.zackchill.livestockmanager;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadAnimalsFragment extends Fragment {

    private TextView Txt_display;
    public ReadAnimalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_animals,
                container, false);
        Txt_display = view.findViewById(R.id.txt_animal_display);
        readAnimals();
        return view;
    }

    public void readAnimals()
    {
        LivestockDbHelper livestockDbHelper = new LivestockDbHelper(getActivity());
        SQLiteDatabase database = livestockDbHelper.getReadableDatabase();

        //TODO read database on a different thread.
        Cursor cursor = livestockDbHelper.readAnimal(database);
        String info = "";
        while (cursor.moveToNext())
        {
            String animalId = Integer.toString(cursor.getInt(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_ID)));

            String animalType = cursor.getString(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_TYPE));

            String animalName = cursor.getString(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_NAME));

            String animalGender = cursor.getString(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_GENDER));

            String animalWeightKg = Float.toString(cursor.getFloat(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_WEIGHTKG)));

            String animalFertile = boolToYesOrNo(cursor.getInt(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_FERTILE)) > 0);

            String animalNeutered = boolToYesOrNo(cursor.getInt(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_NEUTERED)) > 0);

            String animalBirthday = cursor.getString(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_BIRTHDAY));

            String animalDeathday = cursor.getString(cursor.getColumnIndex(
                    LivestockContract.LiveStockEntry.ANIMAL_DEATHDAY));

            info = info + "\n\n" + "Animal Id: " + animalId + "\n" + "Animal Type: "
                    + animalType + "\nAnimal Name: " + animalName + "\n" + "Animal Gender: "
                    + animalGender + "\nAnimal Weight (Kg): " + animalWeightKg + "\n" + "Animal Fertile: "
                    + animalFertile + "\nAnimal Neutered: " + animalNeutered + "\n" + "Animal Birthdate: "
                    + animalBirthday;

            if(animalDeathday != null)
            {
                info = info + "\n" + "Animal Deathday: " + animalDeathday;
            }
            info += "\n";
        }

        Txt_display.setText(info);
        livestockDbHelper.close();
    }

    /*
        Utility function to convert boolean to "yes" or "no" Strings.
     */
    public static String boolToYesOrNo(boolean b)
    {
        if (b)
        {
            return "Yes";
        }
        return "No";
    }

}
