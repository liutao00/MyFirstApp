package com.tao.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    private List<Map<String, String>> data = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("姓名", "风晴雪");
        data.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("姓名", "悭臾");
        data.add(map2);

        ListView lv = (ListView) findViewById(R.id.listView);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/

        if (lv != null) {
            lv.setAdapter(new SimpleAdapter(this,data,android.R.layout.simple_list_item_single_choice,
                    new String[]{"姓名"},            //每行显示一个姓名
                    new int[]{android.R.id.text1}   //名字在text1上显示
            ));
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            /*        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getData()));*/

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long arg3) {
                    //点击后在标题上显示点击了第几行
                    setTitle("你点击了第"+position+"行");

                    EditText editText = (EditText) findViewById(R.id.edit_message);
                    Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                            Toast.LENGTH_SHORT).show();
                    editText.setText(((TextView) view).getText());
                }
            });
        }

    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this , DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


        Intent intent_mail = new Intent(Intent.ACTION_SENDTO);
        intent_mail.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent_mail.putExtra(Intent.EXTRA_EMAIL, "liutao@szhorn.com");
        intent_mail.putExtra(Intent.EXTRA_SUBJECT, "message from android app");
        intent_mail.putExtra(Intent.EXTRA_TEXT, message);
        if (intent_mail.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_mail);
        }



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
