package com.example.ceramiczuhairkhalaf.AppFace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.MapFragment;
import com.example.ceramiczuhairkhalaf.R;
import com.example.ceramiczuhairkhalaf.ShowData.AllTilesFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private ImageButton btnLogOut , btnAllTiles;
    private FirebaseServices fbs;
    private Fragment fragment;
    private RecyclerView rvTilesCards, rvBathSaniTaryCards;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btnAllTiles = getView().findViewById(R.id.btnAllTileHomeFragment);
        btnLogOut = getView().findViewById(R.id.btnLogOutHomeFragment);
        rvTilesCards = getView().findViewById(R.id.rvTilesCardsHomeFragment);
        rvBathSaniTaryCards = getView().findViewById(R.id.rvBathSaniTaryCardsHomeFragment);

        fbs = FirebaseServices.getInstance();
        fragment = new MapFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutMap,fragment).commit();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbs.getAuth().signOut();
                goToMainFragment();
            }
        });
        btnAllTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllTilesFragment();
            }
        });

    }
    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void goToAllTilesFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new AllTilesFragment());
        ft.commit();
    }
}