/*
Assignment InClass08.
Viranchi Deshpande, Dharak Shah
*/
package com.example.viranchi.inclass08_groupnumber11;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link First.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class First extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //static ArrayList<String> words = null;
    EditText edtIngredients;
    ImageButton imgViewAdd;
    Button btnSearch;
    EditText edtMainIngredient;

    public First() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_details);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        edtMainIngredient = (EditText) getView().findViewById(R.id.editTextMainIngredient);
        edtIngredients = (EditText) getView().findViewById(R.id.editTxtAddWords);
        imgViewAdd = (ImageButton) getView().findViewById(R.id.imageButtonAdd);
        btnSearch = (Button) getView().findViewById(R.id.btnSerach);
        imgViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.wordList.size() < 5) {
                    MainActivity.wordList.add(edtIngredients.getText().toString());
                    edtIngredients.setText("");
                    mAdapter = new RecepieAdapter(MainActivity.wordList);
                    mRecyclerView.setAdapter(mAdapter);
                }
                else{
                    Toast.makeText(getActivity(), "Can only add maximum 5 ingredients", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append("http://www.recipepuppy.com/api/?i=");
                for (int i = 0; i < MainActivity.wordList.size(); i++)
                {
                    if (i == MainActivity.wordList.size() - 1) {
                        sb.append(MainActivity.wordList.get(i));
                    }
                    else
                    {
                        sb.append(MainActivity.wordList.get(i) + ",");
                    }
                }
                sb.append("&q="+edtMainIngredient.getText().toString());
                new GetRecepie(getActivity()).execute(sb.toString());
            }
        });

        /*mRecyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecepieAdapter(words);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ArrayList<Recepie> recepieList);
    }
}
