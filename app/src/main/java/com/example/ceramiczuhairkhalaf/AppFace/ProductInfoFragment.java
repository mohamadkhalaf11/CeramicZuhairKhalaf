package com.example.ceramiczuhairkhalaf.AppFace;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ceramiczuhairkhalaf.Adapters.ImageAdapter;
import com.example.ceramiczuhairkhalaf.Classes.Tile;
import com.example.ceramiczuhairkhalaf.Classes.CardSet;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductInfoFragment extends Fragment {
    private ImageButton btnBack;
    private TextView tvProductName ,tvCompany ,tvPrice , tvSize , tvPolished ,tvDesignedIn , tvMadeIn;
    private ImageView ivImage;
    private FirebaseServices fbs;
    private Tile tileInfo;
    private CardSet cs;
    private ArrayList<Tile> tileArrayList;
    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductInfoFragment newInstance(String param1, String param2) {
        ProductInfoFragment fragment = new ProductInfoFragment();
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
        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btnBack = getView().findViewById(R.id.btnBackProductInfoFragment);
        init();
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

    public void init()
    {
        fbs =FirebaseServices.getInstance();
        tvProductName = getView().findViewById(R.id.tvProductNameProductInfoFragment);
        tvCompany = getView().findViewById(R.id.tvCompanyNameProductInfo);
        tvPrice = getView().findViewById(R.id.tvPriceProductInfo);
        tvSize = getView().findViewById(R.id.tvSizeProductInfo);
        tvPolished = getView().findViewById(R.id.tvPolishedProductInfo);
        tvDesignedIn = getView().findViewById(R.id.tvMadeInBathSanitaryInfoFragment);
        tvMadeIn = getView().findViewById(R.id.tvMadeInProductInfo);
        ivImage = getView().findViewById(R.id.ivImageBathSanitaryInfoFragment);
        recyclerView = getView().findViewById(R.id.recycler);
        ArrayList<String> arrayList = new ArrayList<>();
        tileArrayList = new ArrayList<>();

        Bundle args = getArguments();
        if (args != null) {
            cs = args.getParcelable("tiles");
            if (cs != null) {
                String productName = cs.getProductName();
                tvProductName.setText(productName);
                tvCompany.setText(cs.getCompany());
                tvPrice.setText(cs.getPrice()+ " ₪");
                tvSize.setText(cs.getSize());
                tvPolished.setText(cs.getPolishedOrMatt());
                tvDesignedIn.setText(cs.getDesignedIn());
                tvMadeIn.setText(cs.getMadeIn());
                if (cs.getImage() == null || cs.getImage().isEmpty()) {
                    //Picasso.get().load(R.drawable.ic_launcher_background).into(ivImage);
                    arrayList.add("https://images.unsplash.com/photo-1533628635777-112b2239b1c7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
                } else {
                    //Picasso.get().load(cs.getImage()).into(ivImage);
                    arrayList.add(cs.getImage());
                    arrayList.add(cs.getStyleImage());
                }

                // fbs.getFire().collection("tiles").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                // @Override
                // public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //  for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                // Tile til = dataSnapshot.toObject(Tile.class);
                // String tilName=til.getName();
                //  if (tilName.equals(productName)) {
                //   tileInfo = til;
                // }
                //break;

                // }
                //  }
                //}).addOnFailureListener(new OnFailureListener() {
                // @Override
                //public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getActivity(), "No data available", Toast.LENGTH_LONG).show();
                // Log.e("ProductInfoFtagment", e.getMessage());
                //  }
                //});

            }
        }

        //if (tileInfo != null)
        // {
        // tvProductName.setText(tileInfo.getName());
        //     tvCompany.setText(tileInfo.getCompany());
        //  String price = String.valueOf(tileInfo.getPrice());
        // tvPrice.setText(price + " ₪");
        //  String size = String.valueOf(tileInfo.getSize());
        //  tvSize.setText(size);
        // tvDesignedIn.setText(tileInfo.getDesignedIn());
        //  tvMadeIn.setText(tileInfo.getMadeIn());
        // if (tileInfo.isPolished())
        //      tvPolished.setText("Polished");
        //  else tvPolished.setText("Matt");
        // if (tileInfo.getImage() == null || tileInfo.getImage().isEmpty()) {
        //   Picasso.get().load(R.drawable.ic_launcher_background).into(ivImage);
        // } else {
        //     Picasso.get().load(tileInfo.getImage()).into(ivImage);
        // }
        //}
        //tvProductName.setText(cs.getProductName());
        // if (cs.getStyleImage() == null || cs.getStyleImage().isEmpty()) {
        //   Picasso.get().load(R.drawable.ic_launcher_background).into(ivImage);
        // } else {
        //     Picasso.get().load(cs.getStyleImage()).into(ivImage);
        // }
        ImageAdapter adapter = new ImageAdapter(getActivity(),arrayList);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String url) {
                /*
                getActivity().startActivity(new Intent(getActivity(), ImageViewFragment.class).putExtra("image",url),
                        ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"image").toBundle()); */
            }
        });
        recyclerView.setAdapter(adapter);
    }

}