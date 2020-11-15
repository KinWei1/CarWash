package com.example.registration;


import com.example.registration.Model.Results;
import com.example.registration.Remote.IGoogleAPIService;
import com.example.registration.Remote.RetrofitClient;
import com.google.firebase.firestore.auth.User;

public class Common {
    public static final int TIME_SLOT_TOTAL = 10;
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static ShopInfo currentshop;
    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";
    public static Results currentResult;

    public static IGoogleAPIService getGoogleAPIService()
    {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }


    public static String convertTimeSlotToString(int slot) {
        switch (slot)
        {
            case 0:
                return "9:00";
            case 1:
                return "10:00";
            case 2:
                return "11:00";
            case 3:
                return "12:00";
            case 4:
                return "13:00";
            case 5:
                return "14:00";
            case 6:
                return "15:00";
            case 7:
                return "16:00";
            case 8:
                return "17:00";
            case 9:
                return "18:00";
            case 10:
                return "19:00";
            default:
                return "Closed";
        }
    }
}
