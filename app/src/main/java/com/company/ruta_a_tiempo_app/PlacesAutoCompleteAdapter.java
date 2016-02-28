package com.company.ruta_a_tiempo_app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestFactory;
import cz.msebera.android.httpclient.HttpResponse;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
    /*
    *
    *
    * Android key 2
    Key
    AIzaSyA_zJFpOjXqGdVjyqbrd2gS24r1SsXbFR0
    Type
    Android
    Creation date
    Feb 6, 2016, 9:49:01 PM
    Android key 1

    Key
    AIzaSyBKS3rZjdozMrg2Zsao174Qspz-ysvRBtc
    Type
    Android
    Creation date
    Feb 6, 2016, 9:06:05 PM
    *
    *
    *
    *
    * */

    private ArrayList<String> resultList;

    public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    //resultList = autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

    /*public static class PlacesResult {

        @Key("predictions")
        public List<Prediction> predictions;

    }

    public static class Prediction {
        @Key("description")
        public String description;

        @Key("id")
        public String id;
    }*/
}
