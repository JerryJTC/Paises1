package com.example.paises;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaisRes {
        @SerializedName("Results")
        private Map<String, Pais> countryMap;

        public List<Pais> getCountries() {
            return new ArrayList<>(countryMap.values());
        }
}
