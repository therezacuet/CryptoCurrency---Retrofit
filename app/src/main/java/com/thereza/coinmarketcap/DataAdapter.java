package com.thereza.coinmarketcap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thereza.coinmarketcap.data.CoinData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theReza on 2/18/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<CoinData> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public DataAdapter(MainActivity context, ArrayList<CoinData> coinData, PostItemListener postItemListener) {
        mItems = coinData;
        mContext = context;
        mItemListener = postItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleTv;
        PostItemListener mItemListener;
        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(android.R.id.text1);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CoinData item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId(),item.getName(),item.getAvailableSupply(),item.getLastUpdated(),item.getMarketCapUsd());

            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CoinData item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<CoinData> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private CoinData getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }
    public interface PostItemListener {
        void onPostClick(String id, String itemName, String availableSupply, String lastUpdated, String name);
    }

}
