package com.zackchill.livestockmanager;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalHomeFragment extends Fragment implements View.OnClickListener
{
    OnDbOpListener dbOpListener;
    private Button BnSave, BnView, BnDelete, BnUpdate;

    public AnimalHomeFragment() {
        // Required empty public constructor
    }

    public interface OnDbOpListener
    {
        public void dBOpPerformed(int method);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal_home,
                container, false);

        BnSave = view.findViewById(R.id.bn_add_animal);
        BnSave.setOnClickListener(this);
        BnView = view.findViewById(R.id.bn_view_animal);
        BnView.setOnClickListener(this);
        BnUpdate= view.findViewById(R.id.bn_update_animal);
        BnUpdate.setOnClickListener(this);
        BnDelete = view.findViewById(R.id.bn_delete_animal);
        BnDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bn_add_animal:
                dbOpListener.dBOpPerformed(0);
                break;
            case R.id.bn_view_animal:
                dbOpListener.dBOpPerformed(1);
                break;
            case R.id.bn_update_animal:
                dbOpListener.dBOpPerformed(2);
                break;
            case R.id.bn_delete_animal:
                dbOpListener.dBOpPerformed(3);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try
        {
            dbOpListener = (OnDbOpListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement the interface method");
        }

    }
}
