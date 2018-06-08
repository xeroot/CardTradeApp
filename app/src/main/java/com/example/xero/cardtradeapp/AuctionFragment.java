package com.example.xero.cardtradeapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.AuctionService;
import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.IAuctionService;
import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.ILogInService;
import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.LogInService;
import com.example.xero.cardtradeapp.Entities.Auction;
import com.example.xero.cardtradeapp.Entities.Card;
import com.example.xero.cardtradeapp.Entities.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AuctionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatePickerDialog picker;
    EditText name;
    EditText minAmount;
    EditText startDate;
    EditText endDate;
    Button createAuction;
    private OnFragmentInteractionListener mListener;

    public AuctionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AuctionFragment newInstance(String param1, String param2) {
        AuctionFragment fragment = new AuctionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        name =  view.findViewById(R.id.etProductName_auction);
        minAmount = view.findViewById(R.id.etMinValue_auction);
        startDate = view.findViewById(R.id.etStartDate_auction);
        endDate = view.findViewById(R.id.etFinishDate_auction);
        createAuction = view.findViewById(R.id.btCreateAuction_auction);

        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        createAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                Auction auction = new Auction();
                int defaultValue = 0;
                int userId = sharedPref.getInt("userId", defaultValue);
                User user = new User();
                user.setId(userId);
                auction.setUserByIdUserSeller(user);
                auction.setStatus("active");
                auction.setType("normal");
                auction.setUserByIdCurrentUser(null);
                Card card = new Card();
                card.setId(Integer.parseInt(name.getText().toString()));
                auction.setCard(card);

                try {
                    //ARREGLAR LAS FUCKING FECHAS 
                    auction.setBeginDate(SimpleDateFormat.getDateInstance(DateFormat.MEDIUM).parse(startDate.getText().toString()));
                    auction.setEndDate(SimpleDateFormat.getDateInstance(DateFormat.MEDIUM).parse(endDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                auction.setCurrentAmount(BigDecimal.valueOf(0.0));
                auction.setAmount( new BigDecimal(minAmount.getText().toString()));

                PushAuction pushAuction = new PushAuction();
                pushAuction.execute(auction);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auction, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class PushAuction extends AsyncTask<Auction,Boolean,Boolean> {


        @Override
        protected Boolean doInBackground(Auction... strings) {

            ILogInService iLogInService = new LogInService();

            IAuctionService iAuctionService = new AuctionService();
            if(iAuctionService.pushAuction(strings[0])){
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean isValid) {
            super.onPostExecute(isValid);
            //TOASTY
        }
    }
}
