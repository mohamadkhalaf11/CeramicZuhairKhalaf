package com.example.ceramiczuhairkhalaf.LoginSignUpForgetPassword;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetPasswordFragment extends Fragment {
    private EditText etEmail;
    private FirebaseServices fbs;
    private Button btnReset;
    private ImageButton btnBack;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgetPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgetPasswordFragment newInstance(String param1, String param2) {
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();
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
        return inflater.inflate(R.layout.fragment_forget_password, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etEmail = getView().findViewById(R.id.etEmailForgetPasswordFragment);
        btnBack = getView().findViewById(R.id.btnBackForgetPasswordFragment);
        btnReset = getView().findViewById(R.id.btnResetForgetPasswordFragment);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("please wait!");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginFragment();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                fbs.getAuth().sendPasswordResetEmail(etEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Check Your Email", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Check The Email You Entered!", Toast.LENGTH_LONG).show();
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
}