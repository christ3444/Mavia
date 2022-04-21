package com.christ.mavia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private  static  int CAMERA_PERMISSION_CODE = 100;
    private  static  int VIDEO_RECORDE_CODE = 101;

    private Uri videopath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(iscamera()){
            Log.i("Video", "camera detecte");
            getCameraPermission();
        }
        else {
            Log.i("Video", "Pas de camera");
        }


    }




    public void recordVideoBtnPressed(View view){

        recordVideo();
    }

    private boolean iscamera(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            return  true;
        }else {
            return false;
        }
    }


    private void getCameraPermission(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }

    private  void recordVideo(){

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_RECORDE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            videopath = data.getData();
            Log.i("VIDEO_RECORD_TAG", "video record");
        }else if(resultCode== RESULT_CANCELED) {
            Log.i("VIDEO_RECORD_TAG", "video canceld");

        }else Log.i("VIDEO_RECORD_TAG","error video");
    }
}