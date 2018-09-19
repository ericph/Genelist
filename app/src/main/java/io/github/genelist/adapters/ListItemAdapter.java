package io.github.genelist.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.logging.Logger;

import io.github.genelist.R;
import io.github.genelist.listitems.ListItem;
import io.github.genelist.lists.GeneList;
import io.github.genelist.ui.GeneListFragment.OnListFragmentInteractionListener;
import io.github.genelist.util.ImageItem;
import io.github.genelist.util.Util;

/**
 * RecyclerView.Adapter that can display a ListItem and makes a call to the
 * specified OnListFragmentInteractionListener
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.adapters.ListItemAdapter");

    private final GeneList<ListItem> gList;
    private final OnListFragmentInteractionListener olfiListener;

    private static int imageId = R.id.listItemImage;

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
            new DownloadImageTask().download(holder.item.getImage());
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != olfiListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    olfiListener.onListFragmentInteraction(holder.item);
                }
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

    private static void setImageBitmap(Bitmap bmp) {

    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        public DownloadImageTask() {}

        private void download(ImageItem image) {
            execute(image.getUrl());
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap icon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Util.warn(LOG, R.string.err_download_image);
            }
            return icon;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            setImageBitmap(result);
        }
    }
}
