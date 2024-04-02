package com.example.ceramiczuhairkhalaf.AppFace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ceramiczuhairkhalaf.CardSetBathSanitary;
import com.example.ceramiczuhairkhalaf.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BathSanitaryInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BathSanitaryInfoFragment extends Fragment {
    private ImageButton btnBack;
    private CardSetBathSanitary cs;
    private ImageView ivImage;
    private TextView tvProductName , tvPrice , tvSize , tvMadeIn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BathSanitaryInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BathSanitaryInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BathSanitaryInfoFragment newInstance(String param1, String param2) {
        BathSanitaryInfoFragment fragment = new BathSanitaryInfoFragment();
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
        return inflater.inflate(R.layout.fragment_bath_sanitary_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btnBack = getView().findViewById(R.id.btnBackBathSanitaryInfoFragment);
        ivImage = getView().findViewById(R.id.ivImageBathSanitaryInfoFragment);
        tvPrice = getView().findViewById(R.id.tvPriceBathSanitaryInfo);
        tvSize = getView().findViewById(R.id.tvSizeBathSanitaryInfo);
        tvProductName = getView().findViewById(R.id.tvProductNameBathSanitaryInfo);
        tvMadeIn = getView().findViewById(R.id.tvMadeInBathSanitaryInfoFragment);
        Bundle args = getArguments();
        if (args != null) {
            cs = args.getParcelable("bathSanitary");
            if (cs != null) {
                String productName = cs.getName();
                tvProductName.setText(productName);
                tvPrice.setText(cs.getPrice() + " ₪");
                tvSize.setText(cs.getSize());
                tvMadeIn.setText(cs.getMadeIn());
                if (cs.getImage() == null || cs.getImage().isEmpty()) {
                    Picasso.get().load(R.drawable.ic_launcher_background).into(ivImage);
                } else {
                    Picasso.get().load(cs.getImage()).into(ivImage);
                }
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBathSanitaryFragment();
            }
        });
    }

    private void goToBathSanitaryFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new BathSanitaryFragment());
        ft.commit();
    }
}