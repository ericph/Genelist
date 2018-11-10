package io.github.genelist.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woxthebox.draglistview.DragListView;

import io.github.genelist.R;
import io.github.genelist.adapters.GenDragItemAdapter;
import io.github.genelist.adapters.ListItemAdapter;
import io.github.genelist.base.ListItem;
import io.github.genelist.base.GeneList;
import io.github.genelist.listitems.Musician;
import io.github.genelist.util.User;

/**
 * A fragment representing a GeneList of ListItems
 * Activities containing this fragment MUST implement OnListFragmentInteractionListener
 */
public class GeneListFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final int NUM_COLUMNS = 1;

    private OnListFragmentInteractionListener olfiListener;
    private GeneList<ListItem> gList = new GeneList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GeneListFragment() {}

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GeneListFragment newInstance() {
        GeneListFragment fragment = new GeneListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, NUM_COLUMNS);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gene_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ListItemAdapter(gList, olfiListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            olfiListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        olfiListener = null;
    }

    private void setDragListView() {
        DragListView dlView = getActivity().findViewById(R.id.drag_item_recycler_view);
        dlView.setDragListListener(new DragListView.DragListListener() {
            @Override
            public void onItemDragStarted(int pos) {}

            @Override
            public void onItemDragEnded(int startPos, int endPos) {
                if (startPos == endPos)
                    return;
                // reorder list
            }

            @Override
            public void onItemDragging(int a, float b, float c) {}
        });
        dlView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GenDragItemAdapter<Musician> gdiAdapter = new GenDragItemAdapter<>(
                User.getInstance().musicianList, R.layout.musician_item, R.id.image, false);
        dlView.setAdapter(gdiAdapter, false);
        dlView.setCanDragHorizontally(false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * http://developer.android.com/training/basics/fragments/communicating.html
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ListItem item);
    }
}
