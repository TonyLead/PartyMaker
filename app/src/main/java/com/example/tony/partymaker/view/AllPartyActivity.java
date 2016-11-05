package com.example.tony.partymaker.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tony.partymaker.API;
import com.example.tony.partymaker.PartyAdapter;
import com.example.tony.partymaker.R;
import com.example.tony.partymaker.model.Party;



import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AllPartyActivity extends AppCompatActivity {

    private PartyAdapter partyAdapter;
    private ArrayList<Party> arrParty = new ArrayList<>(); // ?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_party);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_all);
        rv.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllPartyActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        partyAdapter = new PartyAdapter(AllPartyActivity.this,arrParty);

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("")
               .addConverterFactory(GsonConverterFactory.create())
               .build();

        final API service = retrofit.create(API.class);

        final Call<Party> call = (Call<Party>) service.getParties();
        call.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Call<Party> call, Response<Party> response) {
                if (response.body().getCode() == 200){
                    arrParty.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<Party> call, Throwable t) {

            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_party, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void q() {
        arrParty = Party.;
    }
}