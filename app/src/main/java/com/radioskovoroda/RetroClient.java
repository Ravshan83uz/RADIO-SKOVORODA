package com.radioskovoroda;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroClient implements APIService {
    private static final String ROOT_URL = "http://api.radioskovoroda.com:3000/api/stream/current/allL";

private static Retrofit getRetrofitInstance() {
    return new Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}

    @Override
    public Call<CurrentSong> getMyJSON() {
        return null;
    }

    {
}




}
