package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends AppCompatActivity {


    ImageView scanImage,historyImage,learnImage,treatmentImage,mapImage,socialImage,logoutImage;
    TextView scanFirst,scanSecond,historyFirst,historySecond,learnFirst,learnSecond,treatmentFirst,treatmentSecond,mapFirst,mapSecond,socialFirst,socialSecond,logoutFirst,logoutSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        scanImage=findViewById(R.id.imageView1);
        scanFirst=findViewById(R.id.textView2);
        scanSecond=findViewById(R.id.textView10);
        historyImage=findViewById(R.id.imageView2);
        historyFirst=findViewById(R.id.textView3);
        historySecond=findViewById(R.id.textView11);
        learnImage=findViewById(R.id.imageView3);
        learnFirst=findViewById(R.id.textView4);
        learnSecond=findViewById(R.id.textView12);
        treatmentImage=findViewById(R.id.imageView4);
        treatmentFirst=findViewById(R.id.textView5);
        treatmentSecond=findViewById(R.id.textView13);
        mapImage=findViewById(R.id.imageView5);
        mapFirst=findViewById(R.id.textView6);
        mapSecond=findViewById(R.id.textView14);
        socialImage=findViewById(R.id.imageView6);
        socialFirst=findViewById(R.id.textView7);
        socialSecond=findViewById(R.id.textView15);
        logoutImage=findViewById(R.id.imageView7);
        logoutFirst=findViewById(R.id.textView8);
        logoutSecond=findViewById(R.id.textView16);






        scanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Prediction.class);
                startActivity(intent);
            }
        });
        scanFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Prediction.class);
                startActivity(intent);
            }
        });
        scanSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Prediction.class);
                startActivity(intent);
            }
        });

        historyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        historyFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        historySecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        learnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Information.class);
                startActivity(intent);
            }
        });
        learnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Information.class);
                startActivity(intent);
            }
        });
        learnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Information.class);
                startActivity(intent);
            }
        });
        treatmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Treatment.class);startActivity(intent);
            }
        });
        treatmentFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(),Treatment.class);
                startActivity(intent);
            }
        });
        treatmentSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Treatment.class);
                startActivity(intent);
            }
        });
        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Map.class);
                startActivity(intent);
            }
        });
        mapFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Map.class);
                startActivity(intent);
            }
        });
        mapSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Map.class);
                startActivity(intent);
            }
        });
        socialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Social.class);
                startActivity(intent);
            }
        });
        socialFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Social.class);
                startActivity(intent);
            }
        });
        socialSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Social.class);
                startActivity(intent);
            }
        });
        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Home.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        logoutFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent ii = new Intent(Home.this, MainActivity.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ii);
            }
        });
        logoutSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent iii = new Intent(Home.this, MainActivity.class);
                iii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iii);

            }
        });
    }




}