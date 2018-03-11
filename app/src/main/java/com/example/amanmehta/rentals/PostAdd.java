package com.example.amanmehta.rentals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;

public class PostAdd extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private Uri imageUri = null,resultUri = null;
    private static final int GALLERY_REQUEST = 1;
    private ProgressDialog mProgress;

    private ImageView imageView1,imageView2,imageView3,imageView4;
    private Button submit;
    private EditText Address,Preference,NoOfRooms,Rate,Water,ElectricityRate,Extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);

        mProgress = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Rooms");
        mStorage = FirebaseStorage.getInstance().getReference();// it links the storage to child directory
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);

        submit = (Button) findViewById(R.id.submit);

        Address = (EditText) findViewById(R.id.Address);
        Preference = (EditText) findViewById(R.id.Preference);
        Rate = (EditText) findViewById(R.id.Rate);
        NoOfRooms = (EditText) findViewById(R.id.NoOfRooms);
        Water = (EditText) findViewById(R.id.Water);
        ElectricityRate = (EditText) findViewById(R.id.ElectricityRate);
        Extra = (EditText) findViewById(R.id.Extra);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });

    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(Integer.MAX_VALUE);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    public void startPosting(){

        final String Address_val = Address.getText().toString().trim();
        final String Rate_val = Rate.getText().toString().trim();
        final String NoOfRooms_val = NoOfRooms.getText().toString().trim();
        final String Preference_val = Preference.getText().toString().trim();


        if(!TextUtils.isEmpty(Address_val) && !TextUtils.isEmpty(Rate_val) && !TextUtils.isEmpty(NoOfRooms_val) && !TextUtils.isEmpty(Preference_val)
                &&imageUri != null){

            StorageReference filePath = mStorage.child("Blogs_Image").child(imageUri.getLastPathSegment());
            mProgress.setMessage("Posting data....");
            mProgress.show();
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("address").setValue(Address_val);
                    newPost.child("rate").setValue(Rate_val);
                    newPost.child("noOfRooms").setValue(NoOfRooms_val);
                    newPost.child("Preference").setValue(Preference_val);
                    newPost.child("image1").setValue(downloadUrl.toString());
                    mProgress.dismiss();
                    Intent homeADD = new Intent(PostAdd.this,Home.class);
                    startActivity(homeADD);
                }
            });
            Toast.makeText(this,"ander baba", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"fill all the requirements", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            resultUri = data.getData();
            CropImage.activity(resultUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                imageUri = result.getUri();
                imageView1.setImageURI(imageUri);
            }
        }
    }
}





/*
        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="@color/darkBlue"
            app:popupTheme="@style/AppTheme.PopupOverlay" />
 */


