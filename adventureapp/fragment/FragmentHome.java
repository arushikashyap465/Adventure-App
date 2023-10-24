package com.example.adventureapp.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.FragmentCommunicator;
import com.example.adventureapp.R;
import com.example.adventureapp.adapter.AdapterAdventure;
import com.example.adventureapp.database.DatabaseHandler;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FragmentHome extends Fragment {
    private RecyclerView rvAdantureList;
    DatabaseHandler db;
    ArrayList<Adventure> adventureList = new ArrayList<>();
    AdapterAdventure adapterAdventure;
    private FragmentCommunicator communicator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        db = new DatabaseHandler(getContext());

        intiView(view);
        getAdventureData();
        return view;

    }

    private void intiView(View view)
    {
        rvAdantureList = view.findViewById(R.id.rv_adavanture_list);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCommunicator)
        {
            communicator = (FragmentCommunicator) context;
        }
        else{
            throw new ClassCastException(context.toString()+" impliment FragmentCommunicator");
        }
    }


    private void getAdventureData()
    {
        adventureList = db.getAllAdvantureData();
        adapterAdventure = new AdapterAdventure(adventureList, new AdapterAdventure.OnItemClickListenar() {
            @Override
            public void onItemClick(Integer position) {
                Toast.makeText(getContext(),adventureList.get(position).getName()+","+adventureList.get(position).getId(),Toast.LENGTH_SHORT ).show();
                Log.e("San",""+adventureList.get(position).getId());
                communicator.passDataFragment(adventureList.get(position).getId());

            }
        });
        rvAdantureList.setHasFixedSize(true);
        rvAdantureList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAdantureList.setAdapter(adapterAdventure);
    }
}
