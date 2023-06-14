package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;


public class Register extends AppCompatActivity {
    EditText username,email,password,confirmPassword,etDate,mobile;
    Button btnRegister;
    TextView goToSignIn;
    DBHelper myDatabase;
    DatePickerDialog.OnDateSetListener setListener;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();



        username=findViewById(R.id.userName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        mobile = findViewById(R.id.mobileId);
        etDate=findViewById(R.id.et_date);

        phoneNoCheck();
        emailCheck();
       // userNameCheck();
        passwordCheck();


        //Unique email
        //invalidMsg = findViewById(R.id.invlaid_msg);
        email.addTextChangedListener(new TextWatcher() {

            private final long DELAY = 100; // Delay time in milliseconds
            private Handler handler = new Handler(Looper.getMainLooper());
            private Runnable runnable;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                handler.removeCallbacks(runnable); // Remove the previous runnable
                runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()) {
                            if (!charSequence.toString().isEmpty()) { // Check if the email is not empty
                                // Check if email is already registered
                                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(charSequence.toString())
                                        .addOnCompleteListener(task -> {
                                            if (task.isSuccessful()) {
                                                List<String> signInMethods = task.getResult().getSignInMethods();
                                                if (signInMethods != null && signInMethods.size() > 0) {
                                                    email.setError("This email is already registered.");
                                                } else {
                                                   // invalidMsg.setText("");
                                                }
                                            } else {
                                                // Handle errors
                                               // invalidMsg.setText(task.getException().getMessage());
                                            }
                                        });
                            } else {
                                email.setError("Invalid email address");
                            }
                        } else {
                            email.setError("Invalid email address");
                        }
                    }
                };
                handler.postDelayed(runnable, DELAY); // Schedule the new runnable


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        goToSignIn=findViewById(R.id.referLogIn);
        btnRegister=findViewById(R.id.logInButton);



        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);




       // myDatabase=new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }

        });

//        btnRegister.setOnClickListener(view -> {
//            String user=username.getText().toString();
//            String email1=email.getText().toString();
//            String Mobile=etMobile.getText().toString();
//            String pass=password.getText().toString();
//            String confirmPass=confirmPassword.getText().toString();
//
//            if(user.equals("") || email1.equals("") || pass.equals("") || confirmPass.equals(""))
//            {
//                Toast.makeText(Register.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//
//                if(pass.equals(confirmPass))
//                {
//                    Boolean userCheckResult=myDatabase.checkUserName(user);
//                    if(userCheckResult==false)
//                    {
//                        Boolean regResult= myDatabase.insertData(user,pass);
//                        if(regResult==true)
//                        {
//                            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                            if (!email1.matches(emailPattern)) {
//                                Toast.makeText(Register.this, "Email is not valid", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            {
//                               if(Mobile.length()==11)
//                               {
//                                   if(Mobile.charAt(0)=='0' && Mobile.charAt(1)=='1' && (Mobile.charAt(2)=='3' || Mobile.charAt(2)=='5' || Mobile.charAt(2)=='6' || Mobile.charAt(2)=='7' || Mobile.charAt(2)=='8' || Mobile.charAt(2)=='9'))
//                                   {
//                                           Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
//                                           Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                                           startActivity(intent);
//
//                                   }
//                               else
//                               {
//                                   Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                               }
//                               }
//                               else
//                               {
//                                   Toast.makeText(Register.this, "Phone number is not valid", Toast.LENGTH_SHORT).show();
//                               }
//
//                            }
//
//
//                        }
//                        else
//                        {
//                            Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else
//                    {
//                        Toast.makeText(Register.this, "User already exists.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(Register.this, "Password not matched.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });

        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

   /* private void userNameCheck() {
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String val = String.valueOf(s);

                String noWhiteSpace = "\\A\\w{4,20}\\z";
                if (val.isEmpty()) {
                    username.setError("Field cannot be empty");

                } else if (val.length() >= 15) {
                    username.setError("Username too long");

                } else if (!val.matches(noWhiteSpace)) {
                    username.setError("White Spaces are not allowed");
                } else if (myDatabase.checkUserName(val)){
                    username.setError("username exist");
                }
                else {
                    username.setError(null);
                    //regUsername.setErrorEnabled(false);

                }
            }
        });
    }*/

    private void emailCheck() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String typedEmail = String.valueOf(s);

                if (!typedEmail.matches(emailPattern)) {
                    Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void passwordCheck() {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String val = password.getText().toString();
                String passwordVal = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
//                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";
                if (val.isEmpty()) {

                    password.setError("Field cannot be empty");
                } else if (!val.matches(passwordVal)) {
                    password.setError("at least 1 digit, any letter, and at least 4 characters");
                } else {
                    password.setError(null);
                    //password.setErrorEnabled(false);

                }
            }
        });
    }

    private void phoneNoCheck() {

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String typedMobile=String.valueOf(s);
                if(typedMobile.length()==11) {
                    if (typedMobile.charAt(0) == '0' && typedMobile.charAt(1) == '1' && (typedMobile.charAt(2) == '3' || typedMobile.charAt(2) == '5' || typedMobile.charAt(2) == '6' || typedMobile.charAt(2) == '7' || typedMobile.charAt(2) == '8' || typedMobile.charAt(2) == '9')) {

                    } else {
                        mobile.setError("Invalid Mobile number");

                    }
                }  else {
                    mobile.setError("Invalid mobile number");


                }
            }
        });

    }



    private void registerUser() {
        String Username=username.getText().toString();
        String pass=password.getText().toString();
        String userEmail = email.getText().toString();
        String date = etDate.getText().toString();
        String confirmPass=confirmPassword.getText().toString();
        String phoneNo = mobile.getText().toString();


        if(!validateEmail() | !validatePhoneNo() | !validatePassword() | !validateUserName() | !validateConfirmPassword()) {
            return;
        }



      /*  progressDialog.setMessage("Wait while registration");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/
        mAuth.createUserWithEmailAndPassword(userEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   // progressDialog.dismiss();
                    UserClass userClass = new UserClass(Username,userEmail,pass,confirmPass,date,phoneNo);

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(userClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        //progressDialog.dismiss();
                                        Toast.makeText(Register.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                                        nextActivity();
                                    }

                                    else{
                                            String s = ""+task.getException();
                                            if(s.length() == 116){
                                                email.setError("Email already exist");
                                            }
                                            else{
                                                Toast.makeText(Register.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    }

                            });
                }



            }
        });

        /*
        Boolean regResult= myDatabase.insertData(user,pass);

        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();*/



    }

   private boolean  validateUserName()
   {
       String val = username.getText().toString();
       String noWhiteSpace = "\\A\\w{4,20}\\z";
       if (val.isEmpty()) {
           username.setError("Field cannot be empty");
           return false;
       } else if (val.length() >= 15) {
           username.setError("Username too long");
           return false;
       } else if (!val.matches(noWhiteSpace)) {
           username.setError("White Spaces are not allowed");
           return false;
       } else {
           username.setError(null);
           //username.setErrorEnabled(false);
           return true;
       }
   }

    private boolean validateEmail() {
        String email1=email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!email1.matches(emailPattern)) {
            Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword()
   {
       String val = password.getText().toString();
       String passwordVal = "^" +
               "(?=.*[0-9])" +         //at least 1 digit
               //"(?=.*[a-z])" +         //at least 1 lower case letter
               //"(?=.*[A-Z])" +         //at least 1 upper case letter
               "(?=.*[a-zA-Z])" +      //any letter
               //"(?=.*[@#$%^&+=])" +    //at least 1 special character
               "(?=\\S+$)" +           //no white spaces
               ".{4,}" +               //at least 4 characters
               "$";
       if (val.isEmpty()) {
           password.setError("Field cannot be empty");
           return false;
       } else if (!val.matches(passwordVal)) {
           password.setError("Password is too weak");
           return false;
       } else {
           password.setError(null);
          // password.setErrorEnabled(false);
           return true;
       }
   }

    private Boolean validateConfirmPassword() {

        String pass = password.getText().toString(),
                confirmPass=confirmPassword.getText().toString();

        if(!pass.equals(confirmPass)) {
            confirmPassword.setError("Password doesn't match");
            return false;
        } else {
            return true;
        }

    }


    private boolean validatePhoneNo() {
        String Mobile=mobile.getText().toString();

        if(Mobile.length()==11) {
            if (Mobile.charAt(0) == '0' && Mobile.charAt(1) == '1' && (Mobile.charAt(2) == '3' || Mobile.charAt(2) == '5' || Mobile.charAt(2) == '6' || Mobile.charAt(2) == '7' || Mobile.charAt(2) == '8' || Mobile.charAt(2) == '9')) {
                return true;

            } else {
                mobile.setError("Invalid mobile number");
                return false;
            }
        }  else {
            mobile.setError("Invalid mobile number");
            return false;
        }
    }

    private void nextActivity(){
        Intent i = new Intent(Register.this,MainActivity.class);
        startActivity(i);
    }


}