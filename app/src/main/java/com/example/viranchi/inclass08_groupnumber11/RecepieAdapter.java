/*
Assignment InClass08.
Viranchi Deshpande, Dharak Shah
*/

package com.example.viranchi.inclass08_groupnumber11;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Viranchi on 30-10-2017.
 */

public class RecepieAdapter extends RecyclerView.Adapter<RecepieAdapter.ViewHolder> {

    static ArrayList<String> mData;

    public RecepieAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String word = mData.get(position);
        holder.textOut.setText(word);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText textOut;
        ImageButton actionBtn;
        public ViewHolder(final View itemView) {
            super(itemView);
            textOut = (EditText) itemView.findViewById(R.id.edtTxtWords);
            actionBtn = (ImageButton) itemView.findViewById(R.id.btnAction);
            /*textOut = (EditText) itemView.findViewById(R.id.textout);
            txtViewSummary = (TextView) itemView.findViewById(R.id.txtViewSummary);
            txtViewEmail = (TextView) itemView.findViewById(R.id.txtViewEmail);*/

            actionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        mData.remove(textOut.getText().toString());
                        Log.d("demo", ""+mData.size());
                        MainActivity.wordList = mData;
                        Log.d("demo","Wordlist Size: " + MainActivity.wordList.size());
                        First.mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
