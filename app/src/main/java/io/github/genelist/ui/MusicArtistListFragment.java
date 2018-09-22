package io.github.genelist.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.github.genelist.R;
import io.github.genelist.listitems.MusicArtist;
import io.github.genelist.adapters.GenDragItemAdapter;
import io.github.genelist.util.User;

import com.woxthebox.draglistview.*;

import java.util.logging.Logger;

public class MusicArtistListFragment extends Fragment {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.ui.MusicArtistListFragment");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_artist_list, container, false);

        setDragListView();

        return view;
    }

    private void setDragListView() {
        DragListView maDLView = getActivity().findViewById(R.id.drag_item_recycler_view);
        maDLView.setDragListListener(new DragListView.DragListListener() {
            @Override
            public void onItemDragStarted(int position) {
                Toast.makeText(getActivity(), "Start - position: " + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                if (fromPosition != toPosition) {
                    Toast.makeText(getActivity(), "End - position: " + toPosition,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemDragging(int a, float b, float c) {}
        });

        maDLView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GenDragItemAdapter<MusicArtist> maListAdapter = new GenDragItemAdapter<>(
                User.getInstance().musicArtistList, R.layout.music_artist_item, R.id.image, false);
        maDLView.setAdapter(maListAdapter, false);
        maDLView.setCanDragHorizontally(false);
    }
}