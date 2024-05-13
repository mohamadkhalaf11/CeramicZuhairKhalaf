package com.example.ceramiczuhairkhalaf.AppFace;

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
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.Adapters.CardBathSanitaryAdapter;
import com.example.ceramiczuhairkhalaf.Classes.CardSetBathSanitary;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BathSanitaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BathSanitaryFragment extends Fragment {

    private ImageButton btnBack;
    private RecyclerView rvBathSanitary;
    private FirebaseServices fbs;
    private CardBathSanitaryAdapter cardAdapter;
    private ArrayList<CardSetBathSanitary> cards;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BathSanitaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BathSanitaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BathSanitaryFragment newInstance(String param1, String param2) {
        BathSanitaryFragment fragment = new BathSanitaryFragment();
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
        return inflater.inflate(R.layout.fragment_bath_sanitary, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        btnBack = getView().findViewById(R.id.btnBackBathSanitaryFragment);
        rvBathSanitary = getView().findViewById(R.id.rvBathSanitaryBathSanitaryFragment);
        fbs = FirebaseServices.getInstance();
        cards = new ArrayList<>();
        cardAdapter = new CardBathSanitaryAdapter(getActivity(),cards);
        rvBathSanitary.setAdapter(cardAdapter);
        rvBathSanitary.setHasFixedSize(true);
        rvBathSanitary.setLayoutManager(new GridLayoutManager(getActivity(),2));

        int spaceInPixels = 10; // Adjust this value as needed
        rvBathSanitary.addItemDecoration(new SpacesItemDecoration1(spaceInPixels));
        fbs.getFire().collection("bathSanitaryCards").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    CardSetBathSanitary cardSet = dataSnapshot.toObject(CardSetBathSanitary.class);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeFragment();
            }
        });
    }

    private void goToHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new HomeFragment());
        ft.commit();
    }
    public class SpacesItemDecoration1 extends RecyclerView.ItemDecoration {
        private final int space;

        public SpacesItemDecoration1(int space) {
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