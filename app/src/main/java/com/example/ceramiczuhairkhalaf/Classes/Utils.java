package com.example.ceramiczuhairkhalaf.Classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.ceramiczuhairkhalaf.Activities.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Utils {
    private static Utils instance;
    private FirebaseServices fbs;
    private String imageStr;

    public Utils()
    {
        fbs = FirebaseServices.getInstance();
    }

    public static Utils getInstance()
    {
        if (instance == null)
            instance = new Utils();

        return instance;
    }
    public void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);
        //builder.setMessage(message);

        // Add a button to dismiss the dialog box
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You can perform additional actions here if needed
                dialog.dismiss();
            }
        });


        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void uploadImage(Context context, Uri selectedImageUri, int i) {
        if (selectedImageUri != null) {
            int progress = 0;
            imageStr = "images/" + UUID.randomUUID() + ".jpg"; //+ selectedImageUri.getLastPathSegment();
            StorageReference imageRef = fbs.getStorage().getReference().child("images/" + selectedImageUri.getLastPathSegment());

            UploadTask uploadTask = imageRef.putFile(selectedImageUri);
            // TODO: progress bar start
            ProgressDialog dialog = new ProgressDialog(context);
            dialog.setTitle("please wait!");
            dialog.show();
              //setProgressValue(progress , context);
            //((MainActivity)context).getProgressBar().setVisibility(View.VISIBLE);
            //((MainActivity)context).getOverlay().setVisibility(View.VISIBLE);
            // show
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //selectedImageUri = uri;
                            if (i == 1)
                                fbs.setSelectedImageURL(uri);
                            else if (i == 2)
                                fbs.setSelectedStyleImageURL(uri);
                            //TODO: Progress bar end
                            dialog.dismiss();
                            //((MainActivity)context).getProgressBar().setVisibility(View.GONE);
                            //((MainActivity)context).getOverlay().setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Utils: uploadImage: ", e.getMessage());
                            //TODO: Progress bar end
                            dialog.dismiss();
                            //((MainActivity)context).getProgressBar().setVisibility(View.GONE);
                            //((MainActivity)context).getOverlay().setVisibility();
                        }
                    });
                    Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "Please choose an image first", Toast.LENGTH_SHORT).show();
        }
    }
    private void setProgressValue(final int progress , Context context) {

        // set the progress
        ((MainActivity)context).getProgressBar().setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10,context);
            }
        });
        thread.start();
    }
}

