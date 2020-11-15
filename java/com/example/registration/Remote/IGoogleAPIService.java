package com.example.registration.Remote;

import com.example.registration.Model.MyPlaces;
import com.example.registration.Model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPLaces(@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlace(@Url String url);
}
