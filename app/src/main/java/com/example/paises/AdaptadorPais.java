package com.example.paises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorPais extends BaseAdapter {
    private Context context;
    private List<Pais> countries;

    public AdaptadorPais(Context context) {
        this.context = context;
        this.countries = new ArrayList<>();
    }

    public void setCountries(List<Pais> countries) {
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Pais getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_country, parent, false);
            holder = new ViewHolder();
            holder.flagImage = convertView.findViewById(R.id.imageViewFlag);
            holder.countryName = convertView.findViewById(R.id.textViewCountryName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Pais pais = getItem(position);
        holder.countryName.setText(pais.getName());

        String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + pais.getAlpha2Code() + ".png";
        Glide.with(context).load(flagUrl).into(holder.flagImage);

        return convertView;
    }

    private static class ViewHolder {
        ImageView flagImage;
        TextView countryName;
    }
}
