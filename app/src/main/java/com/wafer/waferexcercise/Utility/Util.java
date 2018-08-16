package com.wafer.waferexcercise.Utility;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wafer.waferexcercise.MainActivity;
import com.wafer.waferexcercise.R;
import com.wafer.waferexcercise.model.Country;
import com.wafer.waferexcercise.model.Currencies;
import com.wafer.waferexcercise.model.Languages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.HashMap;

public class Util {
    private static final String TAG = "WaferExcercise_util";

    public static ArrayList<Country> parseData(String jsonStr) {

        ArrayList<Country> countries = new ArrayList<>();
        if (jsonStr != null) {
            try {
                JSONArray jsonCountries = new JSONArray(jsonStr);
                // Getting JSON Array node

                // looping through All Countries
                for (int i = 0; i < jsonCountries.length(); i++) {
                    JSONObject c = jsonCountries.getJSONObject(i);
                    Country country = new Country();
                    country.setName(c.getString("name"));
                    JSONArray currencies = c.getJSONArray("currencies");
                    Currencies[] currenciesArrayList = new Currencies[currencies.length()];
                    for (int j = 0; j < currencies.length(); j++) {
                        JSONObject cur = currencies.getJSONObject(j);
                        Currencies curr = new Currencies();
                        curr.setCode(cur.getString("code"));
                        curr.setName(cur.getString("name"));
                        curr.setSymbol(cur.getString("symbol"));

                        currenciesArrayList[j] = curr;
                    }
                    country.setCurrencies(currenciesArrayList);
                    JSONArray jsonLanguages = c.getJSONArray("languages");
                    Languages[] languagesArray = new Languages[jsonLanguages.length()];
                    for (int j = 0; j < jsonLanguages.length(); j++) {
                        JSONObject langs = jsonLanguages.getJSONObject(j);
                        Languages languages = new Languages();
                        languages.setIso639_1(langs.getString("iso639_1"));
                        languages.setIso639_2(langs.getString("iso639_2"));
                        languages.setName(langs.getString("name"));
                        languages.setNativeName(langs.getString("nativeName"));

                        languagesArray[j] = languages;
                    }
                    country.setLanguages(languagesArray);

                    countries.add(country);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json  error: " + e.getMessage());
                Log.e(TAG, "parseData: ",e.getCause() );
            }

        } else {
            Log.e(TAG, "Empty data from server.");

        }
        return countries;
    }
}
