package com.example.paises;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("http://www.geognos.com/api/en/countries/info/all.json")
    Call<PaisRes> getCountries();
}
