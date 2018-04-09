package com.zackchill.livestockmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AnimalHomeFragment.OnDbOpListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null)
        {
            if (savedInstanceState != null)
            {
                return;
            }

            AnimalHomeFragment animalHomeFragment = new AnimalHomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    animalHomeFragment).commit();
        }

    }

    @Override
    public void dBOpPerformed(int method)
    {
        switch (method)
        {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddAnimalFragment()).addToBackStack(null).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReadAnimalsFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
