package com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic;

import android.support.v4.util.Pair;

public interface ILogInService {
    Pair<Boolean,Integer> UserValid(String username, String password);
}
