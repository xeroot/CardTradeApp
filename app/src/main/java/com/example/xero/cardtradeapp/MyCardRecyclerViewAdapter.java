package com.example.xero.cardtradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xero.cardtradeapp.CardFragment.OnListFragmentInteractionListener;
import com.example.xero.cardtradeapp.Entities.Auction;
import com.example.xero.cardtradeapp.Entities.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCardRecyclerViewAdapter extends RecyclerView.Adapter<MyCardRecyclerViewAdapter.ViewHolder> {

    private  List<Card> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCardRecyclerViewAdapter(List<Card> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText("Mana Cost: "+mValues.get(position).getManaCost());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("fragmentKey",2);
                    bundle.putInt("CardId",mValues.get(position).getId());
                    bundle.putString("CardName",mValues.get(position).getName());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Card mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
    public void setfilter(List<Card> listitem){
        mValues = new ArrayList<Card>();
        mValues.addAll(listitem);
        notifyDataSetChanged();
    }
}
