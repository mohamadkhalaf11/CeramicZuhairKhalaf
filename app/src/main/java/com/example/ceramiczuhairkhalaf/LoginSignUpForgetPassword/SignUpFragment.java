package com.example.ceramiczuhairkhalaf.LoginSignUpForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.Activities.DrawerActivity;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.AppFace.HomeFragment;
import com.example.ceramiczuhairkhalaf.AppFace.MainFragment;
import com.example.ceramiczuhairkhalaf.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private EditText etEmail , etPassword , etConfirmPassword , etFullName;
    private Button btnSignUp;
    private ImageButton btnBack;
    private FirebaseServices fbs;
    private TextView tvLogin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etEmail = getView().findViewById(R.id.etEmailSignUpFragment);
        etPassword = getView().findViewById(R.id.etPasswordSignUpFragment);
        etConfirmPassword = getView().findViewById(R.id.etConfirmPasswordSignUpFragment);
        btnSignUp = getView().findViewById(R.id.btnSignUpSignUpFragment);
        tvLogin = getView().findViewById(R.id.tvLoginSignUpFragment);
        etFullName = getView().findViewById(R.id.etFullNameSignUpFragment);
        btnBack = getView().findViewById(R.id.btnBackSignUpFragment);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("please wait!");

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginFragment();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainFragment();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                if (email.trim().isEmpty() && password.trim().isEmpty() && confirmPassword.trim().isEmpty()){
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!confirmPassword.equals(password))
                {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Check Password!", Toast.LENGTH_LONG).show();
                    return;
                }

                fbs.getAuth().createUserWithEmailAndPassword(email , password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // TODO: decide what to do
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Signed Up successfuly", Toast.LENGTH_SHORT).show();
                            //goToHomeFragment();
                            gotoDrawerActivity();
                        }
                        else
                        {
                            dialog.dismiss();
                            // TODO: decide what to do
                        }
                    }
                });
            }
        });
    }
    private void goToLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new LoginFragment());
        ft.commit();
    }
    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void goToHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new HomeFragment());
        ft.commit();
    }
    private void gotoDrawerActivity() {
        Intent i = new Intent(getActivity(), DrawerActivity.class);
        startActivity(i);
    }
}