package com.zackchill.livestockmanager;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadAnimalsFragment extends Fragment
{
    private static final String ANIMALTYPESTRING = "     Animal: ";
    private static final String ANIMALNAMESTRING = "\n         Name:   ";
    private ListView listView;
    private ArrayList<Animal> animalList;
    private ArrayList<String> menuItems;

    public ReadAnimalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.animalslistlayout,
                container, false);

        menuItems = new ArrayList<String>();
        animalList = new ArrayList<Animal>();

        readAnimals();

        listView = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            menuItems
        );


        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id)
            {
                Intent intent = new Intent(getActivity(), AnimalActivity.class);
                /*
                intent.putExtra("AnimalName", menuItems.get(position));
                startActivity(intent);
                */

                Animal animal = animalList.get(position);
                intent.putExtra("ANIMALID", animal.getAnimalId());
                intent.putExtra("ANIMALTYPE", animal.getAnimalType());
                intent.putExtra("ANIMALNAME", animal.getAnimalName());
                intent.putExtra("ANIMALGENDER", animal.getAnimalGender());
                intent.putExtra("ANIMALFERTILE", animal.getAnimalFertile());
                intent.putExtra("ANIMALNEUTERED", animal.getAnimalNeutered());
                intent.putExtra("ANIMALWEIGHT", animal.getAnimalWeightKg());
                intent.putExtra("ANIMALBIRTHDAY", animal.getAnimalBirthday());
                intent.putExtra("ANIMALDEATHDAY", animal.getAnimalDeathday());
                startActivity(intent);
            }
        });

        return view;
    }


    public void readAnimals()
    {
        int count = 1;
        LivestockDbHelper livestockDbHelper = new LivestockDbHelper(getActivity());
        SQLiteDatabase database = livestockDbHelper.getReadableDatabase();

        //TODO read database on a different thread.
        Cursor cursor = livestockDbHelper.readAnimal(database);
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

            //Add animal to ListView String arraylist.
            menuItems.add(Integer.toString(count) + ". "
                    + ANIMALTYPESTRING + animalType
                    + ANIMALNAMESTRING + animalName);

            Animal a = new Animal(animalId, animalType, animalName, animalGender,
                    animalWeightKg, animalFertile, animalNeutered, animalBirthday,
                    animalDeathday);

            animalList.add(a);

            //Increment animal count.
            count++;
        }
        livestockDbHelper.close();
    }

    /*
        Utility function to convert boolean to "yes" or "no" String.
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
