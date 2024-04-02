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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.AppFace.MainFragment;
import com.example.ceramiczuhairkhalaf.CardSetBathSanitary;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;
import com.example.ceramiczuhairkhalaf.Utials;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBathSanitaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBathSanitaryFragment extends Fragment {

    private ImageButton btnBack;
    private Button btnAddProduct;
    private Utials utl;
    private static final int GALLERY_REQUEST_CODE = 123;
    private EditText etName,etSize,etPrice,etMadeIn;
    private ImageView ivImage;
    private FirebaseServices fbs;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddBathSanitaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBathSanitaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBathSanitaryFragment newInstance(String param1, String param2) {
        AddBathSanitaryFragment fragment = new AddBathSanitaryFragment();
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
        return inflater.inflate(R.layout.fragment_add_bath_sanitary, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        btnBack = getView().findViewById(R.id.btnBackAddBathSanitaryFragment);
        etName = getView().findViewById(R.id.etNameBathSanitaryFragment);
        etPrice = getView().findViewById(R.id.etPriceBathSanitaryFragment);
        etSize = getView().findViewById(R.id.etSizeBathSanitaryFragment);
        etMadeIn = getView().findViewById(R.id.etMadeInBathSanitaryFragment);
        ivImage = getView().findViewById(R.id.ivImageAddBathSanitaryFragment);
        btnAddProduct = getView().findViewById(R.id.btnAddProductAddBathSanitaryFragment);
        fbs = FirebaseServices.getInstance();
        utl = Utials.getInstance();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainFragment();
            }
        });

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                String size = etSize.getText().toString();
                String madeIn = etMadeIn.getText().toString();

                if(name.trim().isEmpty() || price.trim().isEmpty() || size.trim().isEmpty() || madeIn.trim().isEmpty())
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
                BathSanitary bathSanitary = new BathSanitary(name , sized , priced , madeIn , fbs.getSelectedImageURL().toString());
                fbs.getFire().collection("BathSanitary").add(bathSanitary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AddCardBathSanitaryToFirebse(name , size , price , madeIn , fbs);
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
    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ivImage.setImageURI(selectedImageUri);
            utl.uploadImage(getActivity(), selectedImageUri, 1);
        }
    }
    private void AddCardBathSanitaryToFirebse(String name , String size , String price , String madeIn , FirebaseServices fbs)
    {
        CardSetBathSanitary cardSetBathSanitary = new CardSetBathSanitary(name , size , price , madeIn , fbs.getSelectedImageURL().toString());
        fbs.getFire().collection("bathSanitaryCards").add(cardSetBathSanitary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
}