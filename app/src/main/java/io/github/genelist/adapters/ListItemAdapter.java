package io.github.genelist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.logging.Logger;

import io.github.genelist.R;
import io.github.genelist.base.ListItem;
import io.github.genelist.base.GeneList;
import io.github.genelist.ui.GeneListFragment.OnListFragmentInteractionListener;

/**
 * RecyclerView.Adapter that can display a ListItem and makes a call to the
 * specified OnListFragmentInteractionListener
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.adapters.ListItemAdapter");

    private final GeneList<ListItem> gList;
    private final OnListFragmentInteractionListener olfiListener;

    public ListItemAdapter(GeneList<ListItem> items, OnListFragmentInteractionListener listener) {
        gList = items;
        olfiListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = gList.get(position);
        holder.rankView.setText(gList.get(position).getId());
        if (holder.item.exists && holder.item.getImage().hasImage()) {
            Glide.with(holder.view).load(holder.item.getImage().getUrl()).into(holder.imageView);
        }

        holder.view.setOnClickListener((View v) -> {
            if (null != olfiListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                olfiListener.onListFragmentInteraction(holder.item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView rankView;
        public final ImageView imageView;
        public ListItem item;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            rankView = view.findViewById(R.id.listItemRank);
            imageView = view.findViewById(R.id.listItemImage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + item.getId() + "'";
        }
    }
}
