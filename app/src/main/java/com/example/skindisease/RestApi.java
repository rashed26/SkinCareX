package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApi extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView recyclerView;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        apiInterface=RetrofitInstance.getRetrofit().create(ApiInterface.class);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RestApi.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);



        apiInterface.getposts().enqueue(new Callback<List<PostPojo>>() {
            @Override
            public void onResponse(Call<List<PostPojo>> call, Response<List<PostPojo>> response) {
                if(response.body().size()>0)
                {
                    adapter=new Adapter(getApplicationContext(), response.body());
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(RestApi.this, "List is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostPojo>> call, Throwable t) {
                Toast.makeText(RestApi.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}