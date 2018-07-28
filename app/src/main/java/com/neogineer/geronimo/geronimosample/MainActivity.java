package com.neogineer.geronimo.geronimosample;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.neogineer.geronimo.geronimosample.data.AppDatabase;
import com.neogineer.geronimo.geronimosample.data.King;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    KingsAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    AppDatabase mDb;

    // behavior for King card buttons.
    private final OnButtonClickedListener mCardButtonsListener
            = new OnButtonClickedListener() {
        @Override
        public void onDetailsClicked(King king) {
            Toast.makeText(MainActivity.this, king.getDetails(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onShareClicked(King king) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, king.getShareableText());
            startActivity(sharingIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecycler();

        mDb = AppDatabase.getInstance(getApplicationContext());

        loadKings();
    }

    private void setupRecycler() {
        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new KingsAdapter(null, mCardButtonsListener);
        mRecycler.setAdapter(mAdapter);
    }

    private void loadKings(){
        LiveData<List<King>> kings = mDb.kingDao().loadAllKings();
        kings.observe(this, (entries) -> {
            Log.d("MainActivity", "Receiving database update from LiveData");
            mAdapter.setKings(entries);
        });
    }

    private void insertHardcodedKings(){
        List<King> kings = getHardcodedList();
        for(King king: kings){
            mDb.kingDao().insertKing(king);
        }
    }

    private King getRandomKing(){
        List<King> kings = getHardcodedList();
        return kings.get( (int) (Math.random()*kings.size()));
    }

    public static List<King> getHardcodedList() {
        ArrayList<King> kings = new ArrayList<>();
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));

        return kings;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_add:
                onAddClicked();
                return true;
            case R.id.menu_item_remove:
                onRemoveClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onAddClicked() {
        AppExecutors.getInstance().diskIO().execute(() ->
                mDb.kingDao().insertKing(
                new King("The king Ragnar", "Vikings", R.drawable.sample_viking)));
    }

    private void onRemoveClicked() {
        if(mAdapter.getItemCount() > 0) {
            King toBeRemoved = mAdapter.getKings().get(0);
            AppExecutors.getInstance().diskIO().execute(() ->
                    mDb.kingDao().deleteKing(toBeRemoved));
        }
    }
}