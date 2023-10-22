package com.example.aclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
ImageView tv1;
Button btn1;
    
Uri image_uri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);//拍照按钮
        
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image_file = new File(getExternalCacheDir(),"temp.jpg");//该方法其实并没有在内存中创建文件，所以还要创建文件
                if(image_file.exists()){
                    image_file.delete();
                }
                try {
                    image_file.createNewFile();//不加try_cache会报错
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                
                image_uri = FileProvider.getUriForFile(
                        getApplicationContext(),
                        "com.example.class.MainActivity.image_Uri",
                        image_file);

                Intent inte = new Intent("android.media.action.IMAGE_CAPTURE");
                inte.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
                
                startActivityForResult(inte,1);

            }
        });
        
        //getContentResolver()

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case  1:
                if(resultCode  == RESULT_OK){
                    Bitmap bitmap = null;
                    try{
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(image_uri));
                        tv1.setImageBitmap(bitmap);
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
        }
    }


}