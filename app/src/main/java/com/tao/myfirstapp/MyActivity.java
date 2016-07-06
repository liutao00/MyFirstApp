package com.tao.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this , DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);



        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream,outputStream2;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = getSharedPreferences("lock", Context.MODE_PRIVATE).edit();
        editor.putString("code", "123456");
        editor.commit();
        Log.d("MyActivity","______________code commit______________");

        try {
            outputStream2 = new FileOutputStream(new File(getExternalFilesDir(
                    null), "message-save.txt"));
            outputStream2.write(message.getBytes());
            outputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
