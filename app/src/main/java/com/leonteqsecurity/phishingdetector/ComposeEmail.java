package com.leonteqsecurity.phishingdetector;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URL;

public class ComposeEmail extends AppCompatActivity {
    public static String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);
    }




    public void  btnAttachFile(View view)
    {
        Toast.makeText(this, "Working on this", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get the selected file's path from the intent's data.
            String fileAttachedString="File Attached: ";
            Uri uri = data.getData();
            String filePath = uri.getPath();

            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
            TextView textView=(TextView) findViewById(R.id.fileAttachedId);
            textView.setText(fileAttachedString+filePath);

            // Perform any necessary operations on the file.
            // ...
        }

    }


    public  void mail(View view)
    {
        File file = new File(filePath);

    }



}