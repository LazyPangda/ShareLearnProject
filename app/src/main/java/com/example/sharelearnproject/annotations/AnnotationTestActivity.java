package com.example.sharelearnproject.annotations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apt_annotation.BindView;
import com.example.aptandroidlib.BindViewTools;
import com.example.sharelearnproject.R;

public class AnnotationTestActivity extends AppCompatActivity {
    //单独一个value可以直接声明值。多个属性时，必须都得声明key
    // @ViewAnnotation(value = "haha",id = 3)

    //@ViewAnnotation(R.id.tv_test)
    //TextView testView;


    @BindView(R.id.tv_test)
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_test);
        //运行时注解
        //AnnotationUtils.init(this);

        //编译时注解
        BindViewTools.bind(this);

        testView.setText("success");


    }
}
