package com.example.sharelearnproject.annotations_fanshe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sharelearnproject.R;

public class AnnotationIntentActivity extends AppCompatActivity {
    @IntentAnnotation("name")
    String name;

    @IntentAnnotation("flag")
    int flag;

    @IntentAnnotation()
    String nullTest;

    @IntentAnnotation("array")
    IntentParcelBean [] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_intent);
        AnnotationUtils.initIntent(this);
        Log.e("AnnotationIntentActivit", "获取的值name：   " + name);
        Log.e("AnnotationIntentActivit", "获取的值flag：   " + flag);
        Log.e("AnnotationIntentActivit", "获取的值nullTest：   " + nullTest);
        Log.e("AnnotationIntentActivit", "获取的值array：   " + array[0].getName());

    }
}
