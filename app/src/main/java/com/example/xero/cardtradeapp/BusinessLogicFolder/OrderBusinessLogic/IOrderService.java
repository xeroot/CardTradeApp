package com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Order;

import java.util.ArrayList;

public interface IOrderService {
    public ArrayList<Order>GetOrders(int id);
}
