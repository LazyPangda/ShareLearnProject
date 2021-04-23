package com.example.sharelearnproject;

import android.util.Log;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
     int [] intArray={12,2,4,57,0,76,3,45,10};



//    @Test
//    public void showHa() {
//        System.out.println("hahahah");
//    }

    /**
     * 选择排序(升序)
     * @param arrays
     * @return
     */
    public int[] xuanzePaixu(int [] arrays){
        for (int i = 0; i < arrays.length; i++) {
            int initMin=arrays[i];
            int index=i;
            for (int j = i+1; j < arrays.length; j++) {
                if (arrays[j]<initMin) {
                    initMin=arrays[j];
                    index=j;
                }
            }

            int temp=arrays[i];
            arrays[i]=initMin;
            arrays[index]=temp;
        }

        return arrays;

    }

    @Test
    public void MYPaixu(){
        int[] ints = xuanzePaixu(intArray);
        System.out.println(Arrays.toString(ints));
    }



}