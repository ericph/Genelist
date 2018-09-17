package io.github.genelist.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;

import io.github.genelist.listitems.ListItem;

import java.util.ArrayList;

public class GenDragItemAdapter<K extends ListItem> extends DragItemAdapter<K, GenDragItemAdapter.ViewHolder> {
    private int layoutId;
    private int grabHandleId;
    private boolean dragOnLongPress;

    public GenDragItemAdapter(ArrayList<K> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        this.layoutId = layoutId;
        this.grabHandleId = grabHandleId;
        this.dragOnLongPress = dragOnLongPress;
        setItemList(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setTag(mItemList.get(position));
    }

    @Override
    public long getUniqueItemId(int position) {
        K item = mItemList.get(position);
        return item.exists ? item.getLongId() : -1;
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {

        ViewHolder(final View itemView) {
            super(itemView, grabHandleId, dragOnLongPress);
        }

        @Override
        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "List item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "List item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
