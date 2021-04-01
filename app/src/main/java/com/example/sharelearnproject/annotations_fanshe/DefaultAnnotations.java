package com.example.sharelearnproject.annotations_fanshe;

import androidx.annotation.IntDef;

public class DefaultAnnotations {
    public static final int RESULT_ERROR=0;
    public static final int  RESULT_SUCCESS=1;

    @IntDef({RESULT_ERROR,RESULT_SUCCESS})
//    @StringDef({"12","23"}) 同理
    public @interface Type{}

    public void setResult(@Type int result){
        System.out.println(result);

    }



    public void Test(){
        setResult(RESULT_ERROR);

    }


}
