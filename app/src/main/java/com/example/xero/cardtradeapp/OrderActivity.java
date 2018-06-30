package com.example.xero.cardtradeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic.IOrderService;
import com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic.OrderService;
import com.example.xero.cardtradeapp.Entities.Order;

public class OrderActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardCost;
    TextView endDateOrder;
    TextView shippingMethod;
    TextView contactName;
    TextView contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void onStart() {
        super.onStart();

        cardName = findViewById(R.id.card_name_order);
        cardCost = findViewById(R.id.card_cost_order);
        endDateOrder = findViewById(R.id.end_date_order);
        shippingMethod = findViewById(R.id.shipping_method_order);
        contactName = findViewById(R.id.contact_name_order);
        contactPhone = findViewById(R.id.phonenumber_order);
        Intent intent = getIntent();
        int idOrder = intent.getIntExtra("Id",0);
        String type = intent.getStringExtra("Type");
        final GetOrderTask getTask = new GetOrderTask();
        getTask.execute(String.valueOf(idOrder),type);
    }

    public void fillSpace(Order order) {
        cardName.setText("Card Name: " + order.getCardName());
        cardCost.setText("Card Cost: " + order.getCost());
        endDateOrder.setText("End Date: " + order.getEndDate());
        shippingMethod.setText("Shipping Method: " + order.getShippingMethod());
        contactName.setText("Contact Name: " + order.getContactName());
        contactPhone.setText("Contact Phone: " + order.getContactPhone());
        return;
    }

    class GetOrderTask extends AsyncTask<String, Order, Order> {
        @Override
        protected Order doInBackground(String... strings) {

            IOrderService orderAPI = new OrderService();
            int id = Integer.parseInt(strings[0]);
            Order order = orderAPI.getOrder(id, strings[1]);

            return order;
        }

        @Override
        protected void onPostExecute(Order order) {
            super.onPostExecute(order);
            fillSpace(order);
        }
    }
}