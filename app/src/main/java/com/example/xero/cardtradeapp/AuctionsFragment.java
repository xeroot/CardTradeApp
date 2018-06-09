package com.example.xero.cardtradeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic.AuctionService;
import com.example.xero.cardtradeapp.Entities.Auction;
//import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;



public class AuctionsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    // Menu Search
    MenuItem itemSearch;
    SearchView searchView;

    // items
    private List<Auction> auctions;

    // adapter
    MyAuctionsRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public AuctionsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AuctionsFragment newInstance(int columnCount) {
        AuctionsFragment fragment = new AuctionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //getActivity().setTitle("xd");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auctions_list, container, false);
        setHasOptionsMenu(true); // esto permite que tenga su propio OptionMenu
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // CALLING REST-FULL API with async method
            auctions = new ArrayList<Auction>(); // reemplazar con el el api
            LoadAuctions loadAuctions = new LoadAuctions();
            loadAuctions.execute();
        }
        return view;
    }


    private class LoadAuctions extends AsyncTask<String,List<Auction>,List<Auction>>{
        @Override
        protected List<Auction> doInBackground(String... strings) {
            List<Auction> l = new AuctionService().getAuctions();
            return l;
        }

        @Override
        protected void onPostExecute(List<Auction> l) {
            super.onPostExecute(l);
            auctions = l;
            // agregando la lista
            adapter = new MyAuctionsRecyclerViewAdapter(auctions, mListener);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Auction item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_item, menu);
        itemSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) itemSearch.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.cardview_light_background));
        // event search...
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return false;}

            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<Auction> filteredList = filter(auctions,newText);
                adapter.setfilter(filteredList);
                return true;
            }
        });
    }

    private List<Auction> filter(List<Auction> pl,String query){
        query = query.toLowerCase();
        final List<Auction> filteredModeList = new ArrayList<>();
        for (Auction model:pl){
            final String text = model.getCardName().toLowerCase();
            if (text.contains(query)) filteredModeList.add(model);
        }
        return filteredModeList;
    }



}
