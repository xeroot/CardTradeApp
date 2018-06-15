package com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Order;

import java.io.OptionalDataException;
import java.util.ArrayList;

public interface IOrderService {
    public ArrayList<Order>GetOrders(int id);
    public Order getOrder(int idorder,String type);
}
