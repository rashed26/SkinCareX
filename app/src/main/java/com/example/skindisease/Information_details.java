package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Information_details extends AppCompatActivity {
    String Disease;
    TextView diseasesTitle, semiTitleDisease, descriptionDisease,signTitleDisease,signDescription,CausesDisease,causesDescription,riskTitleDisease,riskDescription;
    ImageView imageViewDisease;

    DatabaseReference diseasesReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_details);

        Disease=getIntent().getStringExtra("disease");

        //findView all id

        diseasesTitle=findViewById(R.id.diseaseTitle);
        semiTitleDisease=findViewById(R.id.semiTitleDisease);
        descriptionDisease=findViewById(R.id.DescriptionDisease);
        signTitleDisease=findViewById(R.id.signTitleDisease);
        signDescription=findViewById(R.id.signDescription);
        CausesDisease=findViewById(R.id.CausesDisease);
        causesDescription=findViewById(R.id.causesDescription);
        riskTitleDisease=findViewById(R.id.riskTitleDisease);
        riskDescription=findViewById(R.id.riskDescription);
        imageViewDisease = findViewById(R.id.imageViewDisease);

        diseasesReference = FirebaseDatabase.getInstance().getReference("diseasesInfo").child(Disease);
        diseasesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    diseasesTitle.setText(snapshot.child("diseasesTitle").getValue().toString());
                    semiTitleDisease.setText(snapshot.child("semiTitleDisease").getValue().toString());
                   descriptionDisease.setText(snapshot.child("descriptionDisease").getValue().toString());
                   signTitleDisease.setText(snapshot.child("signTitleDisease").getValue().toString());
                  signDescription.setText(snapshot.child("signDescription").getValue().toString());
                    CausesDisease.setText(snapshot.child("CausesDisease").getValue().toString());
                   causesDescription.setText(snapshot.child("causesDescription").getValue().toString());
                   riskTitleDisease.setText(snapshot.child("riskTitleDisease").getValue().toString());
                    riskDescription.setText(snapshot.child("riskDescription").getValue().toString());
                    Glide.with(getApplicationContext()).load(snapshot.child("image").getValue().toString()).into(imageViewDisease);
//                    Toast.makeText(Information_details.this, snapshot.child("image").getValue().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}