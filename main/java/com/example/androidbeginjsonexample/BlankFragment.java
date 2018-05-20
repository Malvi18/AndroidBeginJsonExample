package com.example.androidbeginjsonexample;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlankFragment extends Fragment {
    ArrayList<Model> models;
    RecyclerView recycleView;
    ImgAdapter imgAdapter;
    private String url = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

    public BlankFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        models = new ArrayList<Model>();
        recycleView = view.findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        imgAdapter = new ImgAdapter(getActivity(), models);
        recycleView.setAdapter(imgAdapter);
        new MyAsynck().execute(url);
        return view;


    }

    private class MyAsynck extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("worldpopulation");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    Model model=new Model();
                    model.setCountry(jsonObject1.getString("country"));
                    model.setFlag(jsonObject1.getString("flag"));

                    models.add(model);
                }
                imgAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
