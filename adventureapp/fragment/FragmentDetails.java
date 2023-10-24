package com.example.adventureapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.R;
import com.example.adventureapp.adapter.AdapterAdventure;
import com.example.adventureapp.database.DatabaseHandler;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class FragmentDetails extends Fragment {
    private TextView tvName,tvDesc,tvCost;
    DatabaseHandler db;
    Adventure adventure;
    private int adventureId=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);

        db = new DatabaseHandler(getContext());
        intiView(view);
        if(getArguments() !=null && getArguments().containsKey("id"))
        {
            adventureId = getArguments().getInt("id");
        }
        getAdventureData();
        return view;

    }

    private void intiView(View view)
    {
        tvName = view.findViewById(R.id.tv_advanture_name);
        tvDesc = view.findViewById(R.id.tv_advanture_desc);
        tvCost = view.findViewById(R.id.tv_advanture_cost);

    }

    private void getAdventureData()
    {
        adventure = db.getAdvantureItemById(adventureId);

        tvName.setText(adventure.getName());
        tvDesc.setText(adventure.getDescription());
        tvCost.setText(adventure.getCost());
    }
}
