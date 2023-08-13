package com.example.skindisease;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText userEmail,password;
    MaterialButton loginButton;
    TextView goToRegister;
   // DBHelper myDatabase;

   // ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmail=findViewById(R.id.userEmail);
        password=findViewById(R.id.password);
        goToRegister=findViewById(R.id.referRegister);
        loginButton=findViewById(R.id.logInButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //myDatabase=new DBHelper(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }
 /*   @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }*/
/*    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        int userID = sessionManagement.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            Intent intent=new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
        }
        else{
            //do nothing
        }
    }*/
        public void login()
               {
           String email=userEmail.getText().toString();
           String pass=password.getText().toString();

           if(email.equals("") || pass.equals(""))
           {
               Toast.makeText(MainActivity.this, "Please enter the Credentials.", Toast.LENGTH_SHORT).show();
           }
           else
           {
               mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                         /*  progressDialog.setMessage("Wait while login");
                           progressDialog.setTitle("Login");
                           progressDialog.setCanceledOnTouchOutside(false);
                           progressDialog.show();
                           progressDialog.dismiss();*/
                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                           session();
                       }else{
                          // progressDialog.dismiss();
                           String s = ""+task.getException();
                           if(s.length() == 137){
                               Toast.makeText(MainActivity.this,"Network connection needed",Toast.LENGTH_SHORT).show();
                           }
                           else if(s.length() == 127){
                               password.setError("Invalid Password");
                           }

                           else{
                               Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
               });




              /* Boolean result=myDatabase.checkUserNamePassword(user,pass);

               if(result==true)
               {

                   User user1 = new User(12,user);
                   SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                   sessionManagement.saveSession(user1);
                   Intent intent=new Intent(getApplicationContext(),Home.class);
                   startActivity(intent);
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
               }*/



           }


       }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
            finish();
        }
    }

    private void session(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
            finish();
            nextActivity();
        }
    }
    private void nextActivity(){
        Intent i = new Intent(MainActivity.this,Home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}