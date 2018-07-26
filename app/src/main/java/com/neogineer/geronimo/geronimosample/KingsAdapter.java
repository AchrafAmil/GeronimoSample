package com.neogineer.geronimo.geronimosample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.neogineer.geronimo.geronimosample.data.King;

import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 23/07/2018.
 */
public class KingsAdapter extends RecyclerView.Adapter<KingsAdapter.ViewHolder> {

    private List<King> mKings;

    private OnButtonClickedListener mClickHandler;

    KingsAdapter(List<King> kings, OnButtonClickedListener listener) {
        mKings = kings;
        mClickHandler = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        King k = mKings.get(position);
        viewHolder.image.setBackgroundResource(k.drawable);
        viewHolder.title.setText(k.title);
        viewHolder.desc.setText(k.desc);
    }

    @Override
    public int getItemCount() {
        return mKings.size();
    }

    /**
     * adds a king to the adapter's data set
     * @param position at which to add
     */
    public void add(int position, King king){
        if(mKings != null && mKings.size() >= position){
            mKings.add(position, king);
            notifyItemInserted(position);
        }
    }

    /**
     * removes the king at the specified position from
     * the adapter's data set
     */
    public void remove(int position){
        if(mKings != null && mKings.size() > position){
            mKings.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * see Weird Behavior description at {@link MainActivity}
     */
    public void duplicateFirstElement() {
        if(mKings.isEmpty())
            throw new IllegalStateException("can't duplicate when it's empty");
        mKings.add(0,mKings.get(0));
        notifyItemInserted(0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        Button detailsButton;
        Button shareButton;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            detailsButton = itemView.findViewById(R.id.details_button);
            detailsButton.setOnClickListener(v ->
                    mClickHandler.onDetailsClicked(mKings.get(getAdapterPosition())));

            shareButton = itemView.findViewById(R.id.share_button);
            shareButton.setOnClickListener(v ->
                    mClickHandler.onShareClicked(mKings.get(getAdapterPosition())));

            shareButton = itemView.findViewById(R.id.share_button);
        }
    }
}
