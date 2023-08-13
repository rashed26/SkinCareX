package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5;
    CardView acne,actinic,eczema,nailFungus,psoriasis,seborrheic;
    String Disease="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        textView1=findViewById(R.id.title102);
        textView2=findViewById(R.id.title104);
        textView3=findViewById(R.id.title106);
        textView4=findViewById(R.id.title108);
        textView5=findViewById(R.id.title110);
        acne=findViewById(R.id.acneInformationCardView);
        actinic=findViewById(R.id.actinicInformationCardView);
        eczema=findViewById(R.id.eczemaInformationCardView);
        nailFungus=findViewById(R.id.nailFungusInformationCardView);
        psoriasis=findViewById(R.id.psoriasisInformationCardView);
        seborrheic=findViewById(R.id.seborrheicInformationCardView);


        acne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="acne";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
        actinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="actinic";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
        eczema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="eczema";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
        nailFungus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="nailFungus";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
        psoriasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="psoriasis";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
        seborrheic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease="seborrheic";
                Intent intent=new Intent(getApplicationContext(),Information_details.class);
                intent.putExtra("disease",Disease);
                startActivity(intent);
            }
        });
    }
}