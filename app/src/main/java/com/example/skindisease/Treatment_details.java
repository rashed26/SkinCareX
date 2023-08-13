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
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Treatment_details extends AppCompatActivity {
     String Treatment;
    TextView diagnosisTitle,diagnosisDecription,treatmentTitle,treatmentDescription,selfCareTitle,selfCareDescription;
    DatabaseReference treatmentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        Treatment=getIntent().getStringExtra("treatment");

        //find all id
        diagnosisTitle=findViewById(R.id.diagnosisTitle);
        diagnosisDecription=findViewById(R.id.diagnosisDescription);
        treatmentTitle=findViewById(R.id.treatmentTitle);
        treatmentDescription=findViewById(R.id.treatmentDescription);
        selfCareTitle=findViewById(R.id.selfCareTitle);
        selfCareDescription=findViewById(R.id.selfCareDescription);

        treatmentReference = FirebaseDatabase.getInstance().getReference("treatmentInfo").child(Treatment);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId="ryox2SQKQPU";
                if(Treatment.equals("acne"))
                {
                     videoId = "lAx5gwhmsdo";
                }
                else if(Treatment.equals("actinic"))
                {
                     videoId = "hDmdk6Vy5Aw";
                }
                else if(Treatment.equals("eczema"))
                {
                    videoId = "fmurdUlmaIg";
                }
                else if(Treatment.equals("nailFungus"))
                {
                    videoId = "f1X5aPTzqTw";
                }
                else if(Treatment.equals("psoriasis"))
                {
                    videoId = "MbmizU2O1XY";
                }
                else if(Treatment.equals("seborrheic"))
                {
                    videoId = "7ZT_J4Ec-LA";
                }

                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        treatmentReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    diagnosisTitle.setText(snapshot.child("diagnosisTitle").getValue().toString());
                    diagnosisDecription.setText(snapshot.child("diagnosisDecription").getValue().toString());
                   treatmentTitle.setText(snapshot.child("treatmentTitle").getValue().toString());
                   treatmentDescription.setText(snapshot.child("treatmentDescription").getValue().toString());
                   selfCareTitle.setText(snapshot.child("selfCareTitle").getValue().toString());
                 selfCareDescription.setText(snapshot.child("selfCareDescription").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}