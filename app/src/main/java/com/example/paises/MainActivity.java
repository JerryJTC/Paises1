package com.example.paises;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridViewCountries;
    private AdaptadorPais adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewCountries = findViewById(R.id.gridViewCountries);
        adapter = new AdaptadorPais(this);
        gridViewCountries.setAdapter(adapter);

        setupRetrofit();
        loadCountries();

        gridViewCountries.setOnItemClickListener((parent, view, position, id) -> {
            Pais pais = adapter.getItem(position);
            showCountryDetails(pais);
        });
    }

    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.geognos.com/api/en/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void loadCountries() {
        Call<PaisRes> call = apiService.getCountries();
        call.enqueue(new Callback<PaisRes>() {
            @Override
            public void onResponse(Call<PaisRes> call, Response<PaisRes> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pais> countries = response.body().getCountries();
                    adapter.setCountries(countries);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PaisRes> call, Throwable t) {
                // Manejar el error
            }
        });
    }

    private void showCountryDetails(Pais pais) {
        Detail dialog = new Detail(this, pais);
        dialog.show();
    }
}
