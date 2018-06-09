package com.example.xero.cardtradeapp.BusinessLogicFolder.ProfileBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Profile;

import java.util.ArrayList;

public interface IProfileService {

    ArrayList<Profile>GetProfile();
    Boolean pushProfile(Profile profile);

}
