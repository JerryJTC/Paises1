package com.example.paises;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

public class Detail extends Dialog {
    private Pais pais;

    public Detail(@NonNull Context context, Pais pais) {
        super(context);
        this.pais = pais;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_country_detail);

        ImageView flagImage = findViewById(R.id.imageViewDetailFlag);
        TextView nameText = findViewById(R.id.textViewDetailName);
        TextView capitalText = findViewById(R.id.textViewDetailCapital);
        TextView iso2Text = findViewById(R.id.textViewDetailISO2);
        TextView iso3Text = findViewById(R.id.textViewDetailISO3);
        TextView fipsText = findViewById(R.id.textViewDetailFIPS);
        TextView telPrefixText = findViewById(R.id.textViewDetailTelPrefix);

        String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + pais.getAlpha2Code() + ".png";
        Glide.with(getContext()).load(flagUrl).into(flagImage);

        nameText.setText("Name: " + pais.getName());
        capitalText.setText("Capital: " + pais.getCapital());
        iso2Text.setText("ISO 2: " + pais.getIso2());
        iso3Text.setText("ISO 3: " + pais.getIso3());
        fipsText.setText("FIPS: " + pais.getFips());
        telPrefixText.setText("Tel Prefix: " + pais.getTelPrefix());
    }
}
