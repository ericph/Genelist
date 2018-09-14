package io.github.genelist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import io.github.genelist.R;
import io.github.genelist.listitems.MusicArtist;
import io.github.genelist.lists.GeneList;
import io.github.genelist.util.GenDragItemAdapter;
import io.github.genelist.util.User;

import com.woxthebox.draglistview.*;

public class MusicArtistListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setDragListView();
    }

    private void setDragListView() {
        DragListView maDLView = findViewById(R.id.drag_item_recycler_view);
        maDLView.setDragListListener(new DragListView.DragListListener() {
            @Override
            public void onItemDragStarted(int position) {
                Toast.makeText(MusicArtistListActivity.this, "Start - position: " + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                if (fromPosition != toPosition) {
                    Toast.makeText(MusicArtistListActivity.this, "End - position: " + toPosition,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemDragging(int a, float b, float c) {}
        });

        maDLView.setLayoutManager(new LinearLayoutManager(MusicArtistListActivity.this));
        GenDragItemAdapter<MusicArtist> maListAdapter = new GenDragItemAdapter<>(
                User.getInstance().musicArtistList, R.layout.music_artist_item, R.id.image, false);
        maDLView.setAdapter(maListAdapter, false);
        maDLView.setCanDragHorizontally(false);
    }
}