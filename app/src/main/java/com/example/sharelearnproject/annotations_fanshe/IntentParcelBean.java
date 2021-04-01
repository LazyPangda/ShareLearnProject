package com.example.sharelearnproject.annotations_fanshe;

import android.os.Parcel;
import android.os.Parcelable;

public class IntentParcelBean implements Parcelable {

    private String name;
    private int age;
    private String id;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将数据写入包裹
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeInt(age);
    }




    /**
     * 取出数据，必须保证存入和取出顺序对应，否则数据会错乱
     * @param in
     */
    public IntentParcelBean(Parcel in) {
        name = in.readString();
        id = in.readString();
        age = in.readInt();
    }


    public IntentParcelBean(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public static final Creator<IntentParcelBean> CREATOR = new Creator<IntentParcelBean>() {

        //从parcel中取出数据
        @Override
        public IntentParcelBean createFromParcel(Parcel in) {
            return new IntentParcelBean(in);
        }

        //供外部类反序列化本地数组使用
        @Override
        public IntentParcelBean[] newArray(int size) {
            return new IntentParcelBean[size];
        }
    };


}
