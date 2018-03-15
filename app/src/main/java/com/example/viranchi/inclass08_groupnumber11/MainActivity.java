/*
Assignment InClass08.
Viranchi Deshpande, Dharak Shah
*/
package com.example.viranchi.inclass08_groupnumber11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements First.OnFragmentInteractionListener{

    static ProgressBar progressBar;
    static TextView txtLoading;

    static ArrayList<String> wordList = new ArrayList<String>();

    ArrayList<Recepie> theList = new ArrayList<Recepie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLoading = (TextView) findViewById(R.id.txtLoading);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        txtLoading.setVisibility(View.GONE);

        getFragmentManager().beginTransaction()
                .add(R.id.container,new First(), "FirstFragment")
                .commit();
    }

    @Override
    public void onFragmentInteraction(ArrayList<Recepie> recepieList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RecepieList", recepieList);
        Second nextFragment = new Second();
        nextFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, nextFragment, "SecondFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
