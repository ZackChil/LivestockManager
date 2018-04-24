package com.zackchill.livestockmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AnimalActivity extends AppCompatActivity
{
    TextView textViewName;
    TextView textViewType;
    TextView textViewGender;
    TextView textViewWeight;
    TextView textViewFertile;
    TextView textViewNeutered;
    TextView textViewBirthday;
    TextView textViewDeathday;
    TextView textViewId;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        textViewName = (TextView) findViewById(R.id.animaltextName);
        textViewType = (TextView) findViewById(R.id.animaltextType);
        textViewGender = (TextView) findViewById(R.id.animaltextGender);
        textViewWeight = (TextView) findViewById(R.id.animaltextWeight);
        textViewFertile = (TextView) findViewById(R.id.animaltextFertile);
        textViewNeutered = (TextView) findViewById(R.id.animaltextNeutered);
        textViewBirthday = (TextView) findViewById(R.id.animaltextBirthday);
        textViewDeathday = (TextView) findViewById(R.id.animaltextDeathday);
        textViewId = (TextView) findViewById(R.id.animaltextID);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            textViewName.setText(bundle.getString("ANIMALNAME"));
            textViewType.setText(bundle.getString("ANIMALTYPE"));
            textViewGender.setText(bundle.getString("ANIMALGENDER"));
            textViewWeight.setText(bundle.getString("ANIMALWEIGHT"));
            textViewFertile.setText(bundle.getString("ANIMALFERTILE"));
            textViewNeutered.setText(bundle.getString("ANIMALNEUTERED"));
            textViewBirthday.setText(bundle.getString("ANIMALBIRTHDAY"));
            textViewDeathday.setText(bundle.getString("ANIMALDEATHDAY"));
            textViewId.setText(bundle.getString("ANIMALID"));
        }

    }

}
