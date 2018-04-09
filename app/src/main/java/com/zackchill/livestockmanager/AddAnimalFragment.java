package com.zackchill.livestockmanager;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAnimalFragment extends Fragment
{

    private Button bnSave;
    EditText Type, Name, Gender, WeightKg, Fertile,
            Neutered, Birthday, Deathday;

    public AddAnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_animal, container, false);

        bnSave = view.findViewById(R.id.bn_save);

        Type = view.findViewById(R.id.txt_animal_type);
        Name = view.findViewById(R.id.txt_animal_name);
        Gender = view.findViewById(R.id.txt_animal_gender);
        WeightKg = view.findViewById(R.id.txt_animal_weightKg);
        Fertile = view.findViewById(R.id.txt_animal_fertile);
        Neutered = view.findViewById(R.id.txt_animal_neutered);
        Birthday = view.findViewById(R.id.txt_animal_birthDay);
        Deathday = view.findViewById(R.id.txt_animal_deathDay);

        bnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String type = Type.getText().toString();
                String name = Name.getText().toString();
                String gender = Gender.getText().toString();
                float weightKg = Float.parseFloat(WeightKg.getText().toString());
                boolean fertile = toBool(Fertile.getText().toString());
                boolean neutered = toBool(Neutered.getText().toString());
                String birthday = Birthday.getText().toString();
                String deathday = Deathday.getText().toString();

                LivestockDbHelper livestockDbHelper = new LivestockDbHelper(getActivity());

                //Special case
                if (deathday.toLowerCase().equals("na"))
                {
                    deathday = null;
                }

                //TODO put database writes on different thread.
                SQLiteDatabase database = livestockDbHelper.getWritableDatabase();
                livestockDbHelper.addAnimal(type, name, gender, weightKg,
                        fertile, neutered, birthday, deathday, database);
                livestockDbHelper.close();

                Type.setText("");
                Name.setText("");
                Gender.setText("");
                WeightKg.setText("");
                Fertile.setText("");
                Neutered.setText("");
                Birthday.setText("");
                Deathday.setText("");

                Toast.makeText(getActivity(), "Animal Saved Successfully...",
                        Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

    /*
        Utility function for grabbing a boolean from "yes" or "no" values.
     */
    public static boolean toBool(String s)
    {
        if (s.equals("Yes") || s.equals("yes")
                || s.equals("y") || s.equals("Y"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
