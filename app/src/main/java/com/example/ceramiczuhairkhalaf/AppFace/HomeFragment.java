package com.example.ceramiczuhairkhalaf.AppFace;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.Adapters.CardAdapter;
import com.example.ceramiczuhairkhalaf.Classes.CardSet;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.Activities.MainActivity;
import com.example.ceramiczuhairkhalaf.R;
import com.example.ceramiczuhairkhalaf.ShowData.AllBathSanitaryFragment;
import com.example.ceramiczuhairkhalaf.ShowData.AllTilesFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{
    private ImageButton btnLogOut , btnAllTiles;
    private FirebaseServices fbs;
    private Fragment fragment;
    private RecyclerView rvTilesCards, rvBathSaniTaryCards;
    private ArrayList<CardSet> cards;
    private CardAdapter cardAdapter;
    private ImageView ivLogo;
    private TextView tvAllBathSanitaries;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

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
        ivLogo = getView().findViewById(R.id.ivLogoHomeFragment);
        btnLogOut = getView().findViewById(R.id.btnLogOutHomeFragment);
        rvTilesCards = getView().findViewById(R.id.rvTilesCardsHomeFragment);
        tvAllBathSanitaries = getView().findViewById(R.id.tvAllBathSanitariesHomeFragment);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(),gso);
        cards = new ArrayList<>();
        cardAdapter = new CardAdapter(getActivity(),cards);
        fbs = FirebaseServices.getInstance();
        fragment = new MapFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutMap,fragment).commit();
        rvTilesCards.setAdapter(cardAdapter);
        rvTilesCards.setHasFixedSize(true);
        // Set LinearLayoutManager to horizontal
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTilesCards.setLayoutManager(new GridLayoutManager(getActivity(),2));

        int spaceInPixels = 10; // Adjust this value as needed
        rvTilesCards.addItemDecoration(new SpacesItemDecoration(spaceInPixels));

        tvAllBathSanitaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllBathSanitaryFragment();
            }
        });


        fbs.getFire().collection("Cards").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    CardSet cardSet = dataSnapshot.toObject(CardSet.class);
                    cards.add(cardSet);
                }
                cardAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                Log.e("AllProductsFragment", e.getMessage());
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
                if(acct !=null)
                {
                    gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            getActivity().finish();
                            gotoMainActivity();
                        }
                    });
                }
                fbs.getAuth().signOut();
                getActivity().finish();
                gotoMainActivity();
            }
        });
        btnAllTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllTilesFragment();
            }
        });

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBathSanitaryFragment();
            }
        });

    }

    private void gotoMainActivity()
    {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }

    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void goToAllTilesFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new AllTilesFragment());
        ft.commit();
    }
    private void goToAllBathSanitaryFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new AllBathSanitaryFragment());
        ft.commit();
    }
    private void goToBathSanitaryFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new BathSanitaryFragment());
        ft.commit();
    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}