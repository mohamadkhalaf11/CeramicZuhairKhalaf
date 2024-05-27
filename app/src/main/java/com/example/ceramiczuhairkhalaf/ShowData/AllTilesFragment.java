package com.example.ceramiczuhairkhalaf.ShowData;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.Adapters.TileAdapter;
import com.example.ceramiczuhairkhalaf.Classes.Tile;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.AppFace.HomeFragment;
import com.example.ceramiczuhairkhalaf.Classes.Utils;
import com.example.ceramiczuhairkhalaf.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllTilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllTilesFragment extends Fragment {

    private FirebaseServices fbs;
    private ArrayList<Tile> tilesList,filteredList;
    private RecyclerView rvTiles;
    private FloatingActionButton btnBack;
    private TileAdapter adapter;
    private SearchView srch;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllTilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllTilesFragment newInstance(String param1, String param2) {
        AllTilesFragment fragment = new AllTilesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_tiles, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        fbs = FirebaseServices.getInstance();
        tilesList = new ArrayList<>();
        filteredList = new ArrayList<>();
        rvTiles = getView().findViewById(R.id.rvBathSanitariesAllBathSanitaryFragment);
        adapter = new TileAdapter(tilesList , getActivity());
        rvTiles.setAdapter(adapter);
        rvTiles.setHasFixedSize(true);
        rvTiles.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnBack = getView().findViewById(R.id.btnBackAllBathSanitaryFragment);
        srch = getView().findViewById(R.id.srchAllTilesFragment);
        srch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeFragment();
            }
        });
        fbs.getFire().collection("tiles").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    Tile til = dataSnapshot.toObject(Tile.class);

                    tilesList.add(til);
                }

                adapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                Log.e("AllTilesFragment", e.getMessage());
            }
        });
    }
    private void goToHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new HomeFragment());
        ft.commit();
    }


    private void applyFilter(String query) {
        // TODO: add onBackspace - old and new query
        if (query.trim().isEmpty())
        {
            adapter = new TileAdapter(tilesList,getActivity());
            rvTiles.setAdapter(adapter);
            //myAdapter.notifyDataSetChanged();
            return;
        }
        filteredList.clear();
        for(Tile tile : tilesList)
        {
            if (tile.getName().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(tile.getSize()).toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(tile.getPrice()).toLowerCase().contains(query.toLowerCase()) ||
                tile.getCompany().toLowerCase().contains(query.toLowerCase()) )
            {
                filteredList.add(tile);
            }
        }
        if (filteredList.size() == 0)
        {
            Utils utils = Utils.getInstance();
            utils.showMessageDialog(getActivity(), "No data to show!");
            return;
        }
        adapter = new TileAdapter(filteredList, getContext());
        rvTiles.setAdapter(adapter);

    }
}