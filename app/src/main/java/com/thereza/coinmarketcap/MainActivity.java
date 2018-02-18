package com.thereza.coinmarketcap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thereza.coinmarketcap.data.CoinData;
import com.thereza.coinmarketcap.model.APiService;
import com.thereza.coinmarketcap.model.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private APiService mService;
    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getApiService();

        init();
        LoadResult();
    }

    public void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mAdapter = new DataAdapter(this, new ArrayList<CoinData>(0), new DataAdapter.PostItemListener() {

            public void onPostClick(String id, String itemName, String availableSupply, String lastUpdated, String name) {
                Toast.makeText(MainActivity.this, "Post id is"+ itemName, Toast.LENGTH_SHORT).show();
                /*Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("description",description);
                intent.putExtra("language",language);
                intent.putExtra("owerName",owerName);
                intent.putExtra("stargazers_count",stargazers_count);
                intent.putExtra("created_at",created_at);
                intent.putExtra("updated_at",updated_at);
                intent.putExtra("avaterPath",avaterPath);
                startActivity(intent);*/



            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void LoadResult(){

        mService.getCoinData().enqueue(new retrofit2.Callback<List<CoinData>>() {
            @Override
            public void onResponse(Call<List<CoinData>> call, Response<List<CoinData>> response) {
                List<CoinData> coins = response.body();
                mAdapter.updateAnswers(coins);
            }

            @Override
            public void onFailure(Call<List<CoinData>> call, Throwable t) {

            }

        });
    }
}
