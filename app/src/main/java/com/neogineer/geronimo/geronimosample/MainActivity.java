package com.neogineer.geronimo.geronimosample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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

    /**
     * Weird behavior: while reverse engineering com.geronimoagency.sample
     * I noticed that '+' button has a weird behavior.
     *      - When size is > 1 it adds The King Ragnar at position 1
     *      - When size is 0 it adds King Ragnar at position 0
     *      - When size is 1 :
     *          - if it's the first time size is 1, it duplicates the element at position 0
     *          - if it's not the first time, it behaves as if size was 0.
     */
    private boolean firstTime = true;
    private void onAddClicked() {
        int size = mAdapter.getItemCount();
        King newKing = new King("The king Ragnar", "Vikings", R.drawable.sample_viking);

        if (size == 1 && firstTime){
            mAdapter.duplicateFirstElement();
            firstTime = false;
        }else if(size > 1)
            mAdapter.add(1, newKing);
        else    // size == 0 or 1
            mAdapter.add(0, newKing);
    }

    private void onRemoveClicked() {
        mAdapter.remove(0);
    }
}