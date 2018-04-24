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

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteAnimalFragment extends Fragment
{
    private EditText Id;
    private Button Button_Delete;

    public DeleteAnimalFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_animal, container, false);
        Id = view.findViewById(R.id.txt_delete_id);
        Button_Delete = view.findViewById(R.id.delete_animal_bn);

        Button_Delete.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                deleteAnimal();
            }
        });
        return view;
    }


    private void deleteAnimal()
    {
        LivestockDbHelper livestockDbHelper
                = new LivestockDbHelper(getActivity());
        SQLiteDatabase database = livestockDbHelper.getWritableDatabase();

        int id = Integer.parseInt(Id.getText().toString());

        livestockDbHelper.deleteAnimal(id, database);
        livestockDbHelper.close();

        Id.setText("");
        Toast.makeText(getActivity(), "Animal Removed Successfully..", Toast.LENGTH_SHORT).show();
    }

}
