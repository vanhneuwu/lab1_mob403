package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class Bai1Activity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_message;
    Button btnLoadImage;
    ImageView imgLoadImage;
    String url = "https://img5.thuthuatphanmem.vn/uploads/2022/01/16/logo-fpt-fpt-polytechnic-tach-nen-dep_043151037.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        tv_message = (TextView) findViewById(R.id.tv_message);
        imgLoadImage = (ImageView) findViewById(R.id.imgView);
        btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        btnLoadImage.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
    if (view.getId() == R.id.btnLoadImage){
        loadIamge();
    }
    }

    private void loadIamge(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork(url);
                imgLoadImage.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_message.setText("IMAGE DOWNLOAD SUCCESSFULL");
                        imgLoadImage.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }

    //load image from server
    public Bitmap loadImageFromNetwork(String strURL){
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(strURL);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());


        }catch (Exception e){
            Log.i("ERROR", "Loi: "+ e.toString());
        } return bmp;
    }


}