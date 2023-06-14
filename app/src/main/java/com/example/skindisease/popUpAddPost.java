package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class popUpAddPost extends AppCompatActivity {
    ProgressBar progressBar;
    Button submitBtn;
    ImageView imageView;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_add_post);

        progressBar=findViewById(R.id.popup_progressBar);
        submitBtn=findViewById(R.id.popup_submit);
        imageView=findViewById(R.id.popup_add);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("post");

                reference.child("rashed").setValue("Hello");

                imageView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                submitBtn.setVisibility(View.INVISIBLE);
            }
        });


    }



}