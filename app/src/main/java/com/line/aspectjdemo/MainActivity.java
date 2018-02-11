package com.line.aspectjdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.line.aspectj.ChangeValueAnotation;
import com.line.aspectj.ClickAnnotation;
import com.line.aspectj.MyGet;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @ChangeValueAnotation(value = "new name")
    @ClickAnnotation(declaredName = "collection_count")
    public void collection(View view) {
        Toast.makeText(this, "collection", Toast.LENGTH_SHORT).show();
    }

    @ChangeValueAnotation(value = "http://www.xueba.cn")
    @MyGet(url = "http://www.jyxb.com", clazz = MainActivity.class)
    public void changeUrl(View view) {
        Toast.makeText(this, "changeUrl", Toast.LENGTH_SHORT).show();
    }
}
