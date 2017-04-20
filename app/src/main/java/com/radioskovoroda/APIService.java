package com.radioskovoroda;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("http://api.radioskovoroda.com:3000/api/stream/current/all")
    Call<CurrentSong> getMyJSON();
}

