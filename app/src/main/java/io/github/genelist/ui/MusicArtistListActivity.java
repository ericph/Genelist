package io.github.genelist.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import io.github.genelist.R;
import io.github.genelist.listitems.MusicArtist;
import io.github.genelist.adapters.GenDragItemAdapter;
import io.github.genelist.util.ImageItem;
import io.github.genelist.util.User;
import io.github.genelist.util.Util;

import com.woxthebox.draglistview.*;

import java.io.InputStream;
import java.util.logging.Logger;

public class MusicArtistListActivity extends AppCompatActivity {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.ui.MusicArtistListActivity");

    private static SparseArray<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageViews = new SparseArray<>();

        setDragListView();

        MusicArtist kanye = MusicArtist.getByName("Kanye West");
        if (kanye.exists && kanye.getImage().hasImage()) {
            int imageId = R.id.yeImageView;
            ImageView imageView = findViewById(imageId);
            imageViews.put(imageId, imageView);
            new DownloadImageTask(imageId).download(kanye.getImage());
        }
    }

    protected static void setImageBitmap(int imageId, Bitmap bmp) {
        ImageView image = imageViews.get(imageId);
        image.setImageBitmap(bmp);
        imageViews.delete(imageId);
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

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        int imageId;

        public DownloadImageTask(int imageId) {
            this.imageId = imageId;
        }

        private void download(ImageItem image) {
            execute(image.getUrl());
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Util.warn(LOG, R.string.err_download_image);
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            MusicArtistListActivity.setImageBitmap(imageId, result);
        }
    }
}