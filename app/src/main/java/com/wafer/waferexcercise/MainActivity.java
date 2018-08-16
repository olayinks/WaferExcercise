package com.wafer.waferexcercise;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.wafer.waferexcercise.Utility.CountryAdapter;
import com.wafer.waferexcercise.Utility.HttpUtil;
import com.wafer.waferexcercise.Utility.SwipeListviewController;
import com.wafer.waferexcercise.Utility.Util;
import com.wafer.waferexcercise.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Wafer_Exercise";
    ArrayList<Country> countryArrayList = new ArrayList<>();
    ListView listView;
    private String url = "https://restcountries.eu/rest/v2/all";
    private SwipeRefreshLayout swipeRefreshLayout;
    CountryAdapter adapter;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.country_list);
        adapter = new CountryAdapter(countryArrayList,context);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setDistanceToTriggerSync(30);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ReadCountries().execute();
            }
        });
        listView.setAdapter(adapter);
        new ReadCountries().execute();
        load();
    }

    private void load()
    {
        SwipeListviewController touchListener =
                new SwipeListviewController(
                        listView,
                        new SwipeListviewController.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    countryArrayList.remove(position);
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });
        listView.setOnTouchListener(touchListener);
    }


    public  class ReadCountries extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context,"Fetching Country data",Toast.LENGTH_LONG).show();
            if(!swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(true);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpUtil sh = new HttpUtil();
            // Making a request to url
            // and getting response
            String jsonStr = sh.fetchData(url);
            countryArrayList =  Util.parseData(jsonStr);
            Log.e(TAG, "Response from server: " + jsonStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            swipeRefreshLayout.setRefreshing(false);
            adapter = new CountryAdapter(countryArrayList,context);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
//            ListAdapter adapter = new SimpleAdapter(MainActivity.this, countries,
//                    R.layout.custom_row, new String[]{ "email","mobile"},
//                    new int[]{R.id.email, R.id.mobile});
//            lv.setAdapter(adapter);
        }
    }
}
