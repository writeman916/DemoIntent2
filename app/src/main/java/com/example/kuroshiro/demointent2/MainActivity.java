package com.example.kuroshiro.demointent2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button btCall ;
    Button btGG;
    TextView mTxt;
    Button btRead;
    ImageView img;

    static final int READ_FROM_GALAXY = 1;
    static final int SELECT_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btCall = findViewById(R.id.btCall);
        btGG = findViewById(R.id.btGoogle);
        mTxt = findViewById(R.id.txtPhone);
        btRead = findViewById(R.id.btRead);
        img = findViewById(R.id.imageView);



        btGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });

        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent();
                mIntent.setAction(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse("tel:"+ mTxt.getText().toString()));
                startActivity(mIntent);
            }
        });


        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_FROM_GALAXY);


            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == READ_FROM_GALAXY && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);


            startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        img.setImageURI(data.getData());

        super.onActivityResult(requestCode, resultCode, data);
    }
}
