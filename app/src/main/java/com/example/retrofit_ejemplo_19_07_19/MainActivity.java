package com.example.retrofit_ejemplo_19_07_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.retrofit_ejemplo_19_07_19.Interfaz.JsonPlaceHolderApi;
import com.example.retrofit_ejemplo_19_07_19.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = (TextView)findViewById(R.id.jsonText);
        getPost();
    }

    private void getPost(){

        Retrofit instancia_retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi_onjeto = instancia_retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call_objeto = jsonPlaceHolderApi_onjeto.getPosts();
        call_objeto.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Posts> postsList = response.body();

                for(Posts post: postsList){
                    String content = "";
                    content += "UserId: " + post.getUserId() + "\n";
                    content += "id: " + post.getId() + "\n";
                    content += "title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";
                    mJsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());

            }
        });
    }

}
