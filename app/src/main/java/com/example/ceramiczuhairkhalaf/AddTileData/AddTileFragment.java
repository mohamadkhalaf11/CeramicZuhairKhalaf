package com.example.ceramiczuhairkhalaf.AddTileData;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.CardSet;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.AppFace.MainFragment;
import com.example.ceramiczuhairkhalaf.R;
import com.example.ceramiczuhairkhalaf.Utials;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTileFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int GALLERY_REQUEST_CODE_2 = 124;

    private FirebaseServices fbs;
    private EditText etTileName, etSize, etPrice, etMadeIn, etCompany, etDesignedIn;
    private Button btnAddTile;
    private CheckBox cbPolished;
    private boolean polished;
    private ImageButton btnBack;
    private Utials utl;
    private ImageView ivUpload, ivUploadStyle;
    private ProgressBar progressBar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTileFragment newInstance(String param1, String param2) {
        AddTileFragment fragment = new AddTileFragment();
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
        return inflater.inflate(R.layout.fragment_add_tile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btnBack = getView().findViewById(R.id.btnBackAddTileFragment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainFragment();
            }
        });
        connectComponents();
    }

    private void connectComponents() {
        fbs = FirebaseServices.getInstance();
        utl = Utials.getInstance();
        etTileName = getView().findViewById(R.id.etTileNameAddTileFragment);
        etSize = getView().findViewById(R.id.etSizeAddTileFragment);
        etPrice = getView().findViewById(R.id.etPriceAddTileFragment);
        etMadeIn = getView().findViewById(R.id.etMadeInAddTileFragment);
        etCompany = getView().findViewById(R.id.etCompanyAddTileFragment);
        etDesignedIn = getView().findViewById(R.id.etDesignedInAddTileFragment);
        btnAddTile = getView().findViewById(R.id.btnAddTileAddTileFragment);
        cbPolished = getView().findViewById(R.id.cbPolishedAddTileFragment);
        ivUpload = getView().findViewById(R.id.ivUploadAddTileFragment);
        ivUploadStyle = getView().findViewById(R.id.ivUploadStyleAddTileFragment);
        polished = true;
        progressBar = getView().findViewById(R.id.pbDownloadImageAddTileFragment);
        cbPolished.setChecked(polished);
        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        ivUploadStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStyleGallery();
            }
        });
        cbPolished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPolished.isChecked() == true)
                    polished = true;
                else
                    polished = false;


            }
        });

        btnAddTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etTileName.getText().toString();
                String size = etSize.getText().toString();
                String price = etPrice.getText().toString();
                String madeIn = etMadeIn.getText().toString();
                String company = etCompany.getText().toString();
                String designedIn = etDesignedIn.getText().toString();

                if (name.trim().isEmpty() || size.trim().isEmpty() ||
                        price.trim().isEmpty() || madeIn.trim().isEmpty() || company.trim().isEmpty() || designedIn.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(size) || !TextUtils.isDigitsOnly(price))
                {

                    Toast.makeText(getActivity(), "something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                double sized =  Double.parseDouble(size);
                double priced = Double.parseDouble(price);
                Tile tile1= new Tile(name , sized , priced , madeIn , company , designedIn , polished,fbs.getSelectedImageURL().toString(), fbs.getSelectedStyleImageURL().toString());

                fbs.getFire().collection("tiles").add(tile1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AddCardToFirebase(fbs,etTileName.getText().toString());
                        Toast.makeText(getActivity(), "Successfully added!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Failure AddData : ", e.getMessage());
                    }
                });

            }
        });
    }

    private void AddCardToFirebase(FirebaseServices fbs , String name) {

        CardSet card = new CardSet(fbs.getSelectedStyleImageURL().toString() , name);
        fbs.getFire().collection("Cards").add(card).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(), "Successfully added your product!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Failure AddProduct: ", e.getMessage());
            }
        });
    }

    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }
    private void openStyleGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE_2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ivUpload.setImageURI(selectedImageUri);
            utl.uploadImage(getActivity(), selectedImageUri, 1);
            progressBar.setVisibility(View.GONE);
        }

        if (requestCode == GALLERY_REQUEST_CODE_2 && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ivUploadStyle.setImageURI(selectedImageUri);
            utl.uploadImage(getActivity(), selectedImageUri, 2);
            progressBar.setVisibility(View.GONE);
        }
    }

}