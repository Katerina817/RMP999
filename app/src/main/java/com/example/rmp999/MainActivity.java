package com.example.rmp999;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        EditText edittext=findViewById(R.id.edittext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gettext=edittext.getText().toString();
                if(gettext.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "You have not entered the rabbit characteristic :(", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, gettext);
                    sendIntent.setType("text/plain");
                    //выбор приложения для отправки
                    startActivity(Intent.createChooser(sendIntent, "Поделиться через"));
                }
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getType();
            if (type != null) {
                if (type.indexOf("image/") != -1) {
                    Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
                    ImageView image=findViewById(R.id.rabbit);
                    image.setImageURI(imageUri);
                } else if (type.equals("text/plain")) {
                    // Код для обработки текстовых данных
                    String text = intent.getStringExtra(Intent.EXTRA_TEXT);
                    EditText edit=findViewById(R.id.edittext);
                    if(text != null && text.length() > 0){
                        edit.setText(text);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "The length of the transmitted data is 0", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        }
    }
}