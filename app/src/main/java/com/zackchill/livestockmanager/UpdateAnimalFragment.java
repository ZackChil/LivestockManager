package com.zackchill.livestockmanager;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateAnimalFragment extends Fragment
{
    private EditText Update_id, Update_type, Update_name, Update_gender,
            Update_weight, Update_fertile, Update_neutered, Update_birthday,
            Update_deathday;
    private Button Update_bn;

    public UpdateAnimalFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_animal, container, false);
        Update_id = view.findViewById(R.id.txt_upanimal_id);
        Update_type = view.findViewById(R.id.txt_upanimal_type);
        Update_name = view.findViewById(R.id.txt_upanimal_name);
        Update_gender = view.findViewById(R.id.txt_upanimal_gender);
        Update_weight = view.findViewById(R.id.txt_upanimal_weightKg);
        Update_fertile = view.findViewById(R.id.txt_upanimal_fertile);
        Update_neutered = view.findViewById(R.id.txt_upanimal_neutered);
        Update_birthday = view.findViewById(R.id.txt_upanimal_birthDay);
        Update_deathday = view.findViewById(R.id.txt_upanimal_deathDay);
        Update_bn = view.findViewById(R.id.bn_update_save);

        /*
        Update_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        */

        Update_bn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateContact();
            }
        });
        return view;
    }

    public void updateContact()
    {
        int id = Integer.parseInt(Update_id.getText().toString());
        String type = Update_type.getText().toString();
        String name = Update_name.getText().toString();
        String gender = Update_gender.getText().toString();
        float weightKg = Float.parseFloat(Update_weight.getText().toString());
        boolean fertile = AddAnimalFragment.toBool(Update_fertile.getText().toString());
        boolean neutered = AddAnimalFragment.toBool(Update_neutered.getText().toString());
        String birthday = Update_birthday.getText().toString();
        String deathday = Update_deathday.getText().toString();

        LivestockDbHelper livestockDbHelper = new LivestockDbHelper(getActivity());

        //Special case
        if (deathday.toLowerCase().equals("na"))
        {
            deathday = null;
        }

        //TODO put write on seperate thread.
        SQLiteDatabase database = livestockDbHelper.getWritableDatabase();
        livestockDbHelper.updateAnimal(id,type, name, gender, weightKg,
                fertile, neutered, birthday, deathday, database);
        livestockDbHelper.close();

        Toast.makeText(getActivity(), "Animal Updated...", Toast.LENGTH_SHORT).show();

        Update_id.setText("");
        Update_type.setText("");
        Update_name.setText("");
        Update_gender.setText("");
        Update_weight.setText("");
        Update_fertile.setText("");
        Update_neutered.setText("");
        Update_birthday.setText("");
        Update_deathday.setText("");



    }

}
