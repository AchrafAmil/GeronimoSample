package com.neogineer.geronimo.geronimosample;

import android.annotation.SuppressLint;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        King k = mKings.get(position);
        viewHolder.image.setBackgroundResource(k.getDrawable());
        viewHolder.title.setText(k.getTitle());
        viewHolder.desc.setText(k.getDesc());
        viewHolder.id.setText("id: "+k.getId());
    }

    @Override
    public int getItemCount() {
        return mKings==null ? 0 : mKings.size();
    }

    List<King> getKings(){
        return mKings;
    }

    void setKings(List<King> kings){
        this.mKings = kings;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        TextView id;
        Button detailsButton;
        Button shareButton;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            id = itemView.findViewById(R.id.id);

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
