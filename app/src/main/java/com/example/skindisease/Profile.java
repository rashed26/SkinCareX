package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private ImageView userImage;
    private EditText username,email,password,dOB,mobile;
    private Button updateButton;
    private String selectedDistrict, selectedState;
    private Spinner stateSpinner, districtSpinner;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    TextView textboxSpinner;

    DatabaseReference userDatabaseReference,profileDatabaseReference;
    StorageReference profileStorageReference;

    Uri imageUri;
    Bitmap bitmap;

    String userId = "";

    Boolean imageUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        userImage = findViewById(R.id.editUserImage);

        username = findViewById(R.id.editUserNamesss);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        dOB = findViewById(R.id.editDateOfBirth);
        mobile = findViewById(R.id.editMobile);

        stateSpinner=findViewById(R.id.states);
        stateAdapter=ArrayAdapter.createFromResource(this,R.array.array_states,R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        textboxSpinner=findViewById(R.id.textboxSpinner);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                districtSpinner=findViewById(R.id.district);
                selectedState=stateSpinner.getSelectedItem().toString();
                int parentID=parent.getId();
                if(parentID==R.id.states)
                {
                    switch(selectedState)
                    {
                        case "Select Your Division": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_districts,R.layout.spinner_layout);
                        break;
                        case "Dhaka": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_dhaka_districts,R.layout.spinner_layout);
                            break;
                        case "Chittagong": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chittagong_districts,R.layout.spinner_layout);
                            break;

                        case "Khulna": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_khulna_districts,R.layout.spinner_layout);
                            break;
                        case "Mymensingh": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_mymensingh_districts,R.layout.spinner_layout);
                            break;
                        case "Rajshahi": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_rajshahi_districts,R.layout.spinner_layout);
                            break;
                        case "Rangpur": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_rangpur_districts,R.layout.spinner_layout);
                            break;
                        case "Sylhet": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_sylhet_districts,R.layout.spinner_layout);
                            break;
                        case "Barisal": districtAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_barisal_districts,R.layout.spinner_layout);
                            break;
                        default:break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(districtAdapter);
                    districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedDistrict=districtSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updateButton = findViewById(R.id.updateButton);

        userDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

       // profileDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Userprofile");
        profileStorageReference = FirebaseStorage.getInstance().getReference();

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"Please select file"),101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedState.equals("Select Your Division"))
                {
                    textboxSpinner.setError("Division is required");

                    return;
                }
                else if(selectedDistrict.equals("Select Your District"))
                {
                    textboxSpinner.setError(null);
                    textboxSpinner.setError("District is required");
                    textboxSpinner.requestFocus();
                }
                else
                {
                    textboxSpinner.setError(null);
                    userDatabaseReference.child((userId)).child("username").setValue(username.getText().toString());
                    userDatabaseReference.child((userId)).child("email").setValue(email.getText().toString());
                    userDatabaseReference.child((userId)).child("password").setValue(password.getText().toString());
                    userDatabaseReference.child((userId)).child("bDate").setValue( dOB.getText().toString());
                    userDatabaseReference.child((userId)).child("phoneNo").setValue(mobile.getText().toString());


                    if(imageUpdated) {
                        updateProfile();
                    }



//                userDatabaseReference.child((userId)).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()){
//                            String name = snapshot.child("username").getValue().toString();
//                            updateProfile(name);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
                }

            }
        });

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == this.RESULT_OK) {
            imageUri = data.getData();
                //InputStream inputStream = getContentResolver().openInputStream(imageUri);
                //bitmap = BitmapFactory.decodeStream(inputStream);
                userImage.setImageURI(imageUri);

                imageUpdated = true;
        }
    }




    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = firebaseUser.getUid();

        userDatabaseReference.child((userId)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setText(snapshot.child("username").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    password.setText(snapshot.child("password").getValue().toString());
                    dOB.setText(snapshot.child("bDate").getValue().toString());
                    mobile.setText(snapshot.child("phoneNo").getValue().toString());

                    if(!snapshot.child("dp").getValue().toString().equals("-")) {
                        Glide.with(getApplicationContext()).load(snapshot.child("dp").getValue().toString()).into(userImage);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void updateProfile(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File Uploader");
        progressDialog.show();

        final StorageReference uploader = profileStorageReference.child("profileimages/"+"img"+System.currentTimeMillis());
        uploader.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                userDatabaseReference.child((userId)).child("dp").setValue(uri.toString());

//                                final Map<String, Object> map = new HashMap<>();
//                                map.put("uimage",uri.toString());
//                                map.put("uname",name);
//
//
//                                profileDatabaseReference.child(userId).addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if(snapshot.exists()){
//                                            profileDatabaseReference.child(userId).updateChildren(map);
//                                        }
//                                        else{
//                                            profileDatabaseReference.child(userId).setValue(map);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });

                                progressDialog.dismiss();
                                Toast.makeText(Profile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded "+(int) progress + "%");
                    }
                });
    }


}