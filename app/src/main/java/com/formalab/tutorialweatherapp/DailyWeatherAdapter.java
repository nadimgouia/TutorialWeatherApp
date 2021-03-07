package com.formalab.tutorialweatherapp;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.Locale;

class DailyWeatherAdapter extends ArrayAdapter<Weather> {

    private Context context;
    private List<Weather>  weatherList;

    public DailyWeatherAdapter(@NonNull Context context, @NonNull List<Weather> weatherList) {
        super(context, 0, weatherList);
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false);

        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvTemp = convertView.findViewById(R.id.tvTemp);
        ImageView iconWeather = convertView.findViewById(R.id.iconWeather);

        Weather weather = weatherList.get(position);
        tvTemp.setText(weather.getTemp()+" °C");

        Ion.with(context)
                .load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png")
                .intoImageView(iconWeather);

        Date date = new Date(weather.getDate()*1000);
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM yy", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone(weather.getTimeZone()));
        tvDate.setText(dateFormat.format(date));

        return convertView;
    }

}
