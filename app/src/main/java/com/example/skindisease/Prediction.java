package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Element;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.skindisease.ml.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Prediction extends AppCompatActivity {

    TextView result, confidence;
    ImageView imageView;
    Button picture,saveButton;
    int imageSize = 224;
    DatabaseReference predictionDatabaseReference,userRef;
    StorageReference predictionStorageReference;
    Uri imageUriGlobal;
    Bitmap bitmap;
    String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);
        saveButton=findViewById(R.id.saveButton);

        //In order to show username
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        predictionDatabaseReference= FirebaseDatabase.getInstance().getReference("prediction").child(userId);
        predictionStorageReference = FirebaseStorage.getInstance().getReference("predictions");

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    } else {
                        //Request camera permission if we don't have it.
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRef.child(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String username = snapshot.child("username").getValue().toString();
                            saveData(username);
//                            Toast.makeText(Prediction.this, username, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

          /*      if(uploadTask!=null && uploadTask.isInProgress()){
                    Toast.makeText(ImageReview.this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
                }

                else{
                    saveData();
                }*/
            }
        });
    }

    public  void classifyImage(Bitmap image)
    {
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

            ByteBuffer byteBuffer=ByteBuffer.allocateDirect(4*imageSize*imageSize*3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int [] intValues=new int[imageSize*imageSize];
            image.getPixels(intValues,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());
            int pixel=0;
            for(int i=0;i<imageSize;i++)
            {
                for(int j=0;j<imageSize;j++)
                {
                    int val=intValues[pixel++];
                    byteBuffer.putFloat(((val>>16)&0xFF)*(1.f/225.f));
                    byteBuffer.putFloat(((val>>8)&0xFF)*(1.f/225.f));
                    byteBuffer.putFloat((val&0xFF)*(1.f/225.f));


                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences= ((TensorBuffer) outputFeature0).getFloatArray();
            int maxPos=0;
            float maxConfidence=0;
            for(int i=0; i<confidences.length;i++)
            {
                if(confidences[i]>maxConfidence){
                    maxConfidence=confidences[i];
                    maxPos=i;
                }
            }
            String [] classes={"Acne","Actinic","Eczema","Nail Fungus","Psoriasis","Seborrheic"};
            result.setText(classes[maxPos]);
            String s="";
            for(int i=0;i<classes.length;i++)
            {
                s+=String.format("%s: %.1f%%\n",classes[i],confidences[i]*100);
            }
            confidence.setText(s);
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            int dimension=Math.min(image.getWidth(), image.getHeight());
            image= ThumbnailUtils.extractThumbnail(image,dimension,dimension);
            imageView.setImageBitmap(image);
            image=Bitmap.createScaledBitmap(image,imageSize,imageSize,false);


            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), image, "Title", null);
            imageUriGlobal = Uri.parse(path);

            classifyImage(image);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class FLOAT32 {
    }
    //Image Extension
    public String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        imageUriGlobal = imageUri;
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void saveData(String username){
        String imageName = result.getText().toString().trim();


        try {
            if (imageName.isEmpty()) {
                result.setError("You do not have any disease name");

            } else {
                StorageReference ref = predictionStorageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUriGlobal));

                ref.putFile(imageUriGlobal)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(Prediction.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                while (!uriTask.isSuccessful()) ;
                                Uri downloadUri = uriTask.getResult();

                                Upload upload = new Upload(imageName, downloadUri.toString(), username);
                                String uploadId = predictionDatabaseReference.push().getKey();
                                predictionDatabaseReference.child(uploadId).setValue(upload);
                                nextActivity();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Prediction.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        } catch (Exception e) {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextActivity(){
        result.setText("");
        Intent intent = new Intent(Prediction.this, Home.class);
        startActivity(intent);
    }
}