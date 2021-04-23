package com.example.sharelearnproject.suanfa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sharelearnproject.R;

import java.util.Arrays;

public class PaixuActivity extends AppCompatActivity {
    private int [] intArray={12,2,4,57,0,76,3,45,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paixu);
        //int[] ints = maoPaoAsc(intArray);
        int[] ints = xuanzePaixu(intArray);
        Log.e("PaixuActivity", "排序的结果："+ Arrays.toString(ints));
    }

    /**
     * 冒泡排序升序
     * @param arrays
     */
    public int[] maoPaoAsc(int [] arrays){
        //外层表示循环次数。
        // 升序：每次选出最大数放在数组最后
        //降序：每次选出最小数放在数组最后
        for (int i = 0; i < arrays.length; i++) {
            //内层循环：每次相邻两个数对比，然后调换位置
            for (int j = 0; j < arrays.length - i-1; j++) {

                if (arrays[j]>arrays[j+1]) {
                    int temp=arrays[j+1];
                    arrays[j+1]=arrays[j];
                    arrays[j]=temp;
                }

                //降序
//                if (arrays[j]<arrays[j+1]) {
//                    int temp=arrays[j+1];
//                    arrays[j+1]=arrays[j];
//                    arrays[j]=temp;
//                }

            }
        }

        return arrays;
    }


    /**
     * 选择排序
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


    /**
     * 插入排序(升序)
     * @param args
     */
    public int[] charuPaixu(int[] args) {

        int arr[] = {7, 5, 3, 2, 4};

        //插入排序
        for (int i = 1; i < arr.length; i++) {
            //外层循环，从第二个开始比较
            for (int j = i; j > 0; j--) {
                //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    //如果不小于，说明插入完毕，退出内层循环
                    break;
                }
            }
        }

        return args;
    }



}
