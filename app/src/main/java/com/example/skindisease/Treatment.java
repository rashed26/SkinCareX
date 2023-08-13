package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Treatment extends AppCompatActivity {

    CardView acneTreatment,actinicTreatment,eczemaTreatment,nailFungusTreatment,psoriasisTreatment,seborrheicTreatment;
    String Treatment="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);


        acneTreatment=findViewById(R.id.acneTreatmentCardView);
        actinicTreatment=findViewById(R.id.actinicTreatmentCardView);
        eczemaTreatment=findViewById(R.id.eczemaTreatmentCardView);
        nailFungusTreatment=findViewById(R.id.nailFungusTreatmentCardView);
        psoriasisTreatment=findViewById(R.id.psoriasisTreatmentCardView);
        seborrheicTreatment=findViewById(R.id.seborrheicTreatmentCardView);

        acneTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="acne";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });
        actinicTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="actinic";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });
        eczemaTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="eczema";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });
        nailFungusTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="nailFungus";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });
        psoriasisTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="psoriasis";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });
        seborrheicTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Treatment="seborrheic";
                Intent intent=new Intent(getApplicationContext(),Treatment_details.class);
                intent.putExtra("treatment",Treatment);
                startActivity(intent);
            }
        });


    }
}