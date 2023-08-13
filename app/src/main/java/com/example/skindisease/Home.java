package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends AppCompatActivity {

    ImageView scanImage,historyImage,learnImage,treatmentImage,mapImage,socialImage,gsappImage,sassImage;
    TextView scanFirst,scanSecond,historyFirst,historySecond,learnFirst,learnSecond,treatmentFirst,treatmentSecond,mapFirst,mapSecond,socialFirst,socialSecond,gsappFirst,gsappSecond,sassFirst,sassSecond;
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
        gsappImage=findViewById(R.id.imageView7);
        gsappFirst=findViewById(R.id.textView8);
        gsappSecond=findViewById(R.id.textView16);
        sassImage=findViewById(R.id.imageView8);
        sassFirst=findViewById(R.id.sass);
        sassSecond=findViewById(R.id.textView17);






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
                Intent intent=new Intent(getApplicationContext(),History.class);
                startActivity(intent);
            }
        });
        historyFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),History.class);
                startActivity(intent);
            }
        });
        historySecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),History.class);
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
        gsappImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Gsapp.class);
                startActivity(intent);
            }
        });
        gsappFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Gsapp.class);
                startActivity(intent);
            }
        });
        gsappSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), Gsapp.class);
                startActivity(intent);

            }
        });
        sassImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Sass.class);
                startActivity(intent);
            }
        });
        sassFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Sass.class);
                startActivity(intent);
            }
        });
        sassSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Home.this, Sass.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        MenuItem item = menu.findItem(R.id.action_add_post);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_profile:
                Intent i = new Intent(Home.this, Profile.class);
                startActivity(i);
                break;
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent ii = new Intent(Home.this, MainActivity.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ii);
                break;

            case R.id.action_rating:
                Intent iii = new Intent(Home.this, Rating.class);
                startActivity(iii);
                break;

            case R.id.action_restApi:
                Intent iv = new Intent(Home.this, RestApi.class);
                startActivity(iv);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}