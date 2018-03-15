/*
Assignment InClass08.
Viranchi Deshpande, Dharak Shah
*/
package com.example.viranchi.inclass08_groupnumber11;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Viranchi on 25-09-2017.
 */

public class GetRecepie extends AsyncTask<String, Integer, ArrayList<Recepie>> {

    MainActivity activity;
    ProgressBar progress;
    TextView txtLoading;

    public GetRecepie(Activity activity) {
        this.activity = (MainActivity) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = activity.progressBar;
        txtLoading = activity.txtLoading;
        progress.setVisibility(View.VISIBLE);
        txtLoading.setVisibility(View.VISIBLE);
        //progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<Recepie> doInBackground(String... params) {

        BufferedReader br = null;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            publishProgress(2);
            con.setRequestMethod("GET");
            if(con.getResponseCode()== HttpURLConnection.HTTP_OK){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                //publishProgress(3);
                while(line!=null){
                    sb.append(line);
                    line = br.readLine();
                }
                ArrayList<Recepie> list =  RecepieUtil.JSONRecepieParser.parseRecepie(sb.toString());


                return list;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            try{
                br.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Recepie> recepies) {
        txtLoading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        if (recepies.size() < 1)
        {
            Toast.makeText(activity, "No recepie found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            activity.onFragmentInteraction(recepies);
        }
        super.onPostExecute(recepies);

    }
}
