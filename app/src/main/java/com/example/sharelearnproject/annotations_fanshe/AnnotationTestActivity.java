package com.example.sharelearnproject.annotations_fanshe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    IntentParcelBean [] intentParceArray=new IntentParcelBean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_test);
        //运行时注解
        //AnnotationUtils.init(this);

        //编译时注解
        BindViewTools.bind(this);

        for (int i = 0; i < 3; i++) {
            IntentParcelBean bean = new IntentParcelBean("name:" + i, i, "身份证：" + i);
           intentParceArray[i]=bean;


        }

        testView.setText("click me to intent");
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnnotationTestActivity.this,AnnotationIntentActivity.class);
                intent.putExtra("name","姓名");
                intent.putExtra("flag",33);
                intent.putExtra("nullTest","空值测试");
                intent.putExtra("array",intentParceArray);
                startActivity(intent);
            }
        });


    }
}
