package com.neogineer.geronimo.geronimosample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.neogineer.geronimo.geronimosample.data.King;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    KingsAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

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
    }

    private void setupRecycler() {
        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new KingsAdapter(getHardcodedList(), mCardButtonsListener);
        mRecycler.setAdapter(mAdapter);
    }

    private List<King> getHardcodedList() {
        ArrayList<King> kings = new ArrayList<>();
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));

        return kings;
    }
}