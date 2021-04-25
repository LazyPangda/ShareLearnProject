package com.example.sharelearnproject.shejimoshi.builder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharelearnproject.R;
import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.EnjoyRetrofit;
import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.api.EnjoyWeatherApi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**创建者模式（Builder模式）
 * 如：创建retrofit对象，创建dialog对象
 * 建造者模式的好处：将一个复杂对象的构建和它的表示分离，可以使使用者不必知道内部组成的细节。
 *
 */
public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        EnjoyRetrofit retrofit = new EnjoyRetrofit.Builder().baseUrl("").build();
        EnjoyWeatherApi enjoyWeatherApi = retrofit.create(EnjoyWeatherApi.class);
        Call call = enjoyWeatherApi.getWeather("", "");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }
}
