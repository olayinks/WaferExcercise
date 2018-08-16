package com.wafer.waferexcercise.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wafer.waferexcercise.R;
import com.wafer.waferexcercise.model.Country;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> implements View.OnClickListener{



        private ArrayList<Country> dataSet;
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            TextView txtName;
            TextView txtCurrency;
            TextView txtLanguage;
            Button deleteButton;
        }

        public CountryAdapter(ArrayList<Country> data, Context context) {
            super(context, R.layout.custom_row, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            Country dataModel=(Country) object;

//            switch (v.getId())
//            {
//                case R.id.item_info:
//                    Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                            .setAction("No action", null).show();
//                    break;
//            }
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Country country = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.custom_row, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.country_name);
                viewHolder.txtLanguage = (TextView) convertView.findViewById(R.id.language_name);
                viewHolder.txtCurrency = (TextView) convertView.findViewById(R.id.currency_name);
              //  viewHolder.deleteButton = (Button) convertView.findViewById(R.id.delete_button);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

//            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.txtName.setText(country.getName().toUpperCase());
            viewHolder.txtLanguage.setText(country.getLanguages()[0].getName().toUpperCase());
            viewHolder.txtCurrency.setText(country.getCurrencies()[0].getName().toUpperCase());
          //  viewHolder.deleteButton.setOnClickListener(this);
//            viewHolder.deleteButton.setTag(position);
            // Return the completed view to render on screen
//            convertView.setOnTouchListener(new View.OnTouchListener() {
//                float downX, downY, nowX, nowY, diffX, diffY;
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//
//
//                        switch (event.getAction()) {
//
//                            case MotionEvent.ACTION_DOWN:
//
////                                downX = event.getRawX();
////                                downY = event.getRawY();
//                                break;
//
//                            case MotionEvent.ACTION_MOVE:
//
//                                nowX = event.getRawX();
//                                nowY = event.getRawY();
//
//                                diffX = nowX - downX;
//                                diffY = nowY - downY;
//
//                                v.setTranslationX(diffX);
//                                v.setTranslationY(diffY);
//
//                                break;
//
//                            case MotionEvent.ACTION_UP:
//
//                                break;
//                        }
//                        return true;
//
//                }
//            });
            return convertView;
        }
    }


