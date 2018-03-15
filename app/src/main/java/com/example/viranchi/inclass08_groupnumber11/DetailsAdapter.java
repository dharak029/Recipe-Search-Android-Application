/*
Assignment InClass08.
Viranchi Deshpande, Dharak Shah
*/
package com.example.viranchi.inclass08_groupnumber11;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Viranchi on 30-10-2017.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    static ArrayList<Recepie> mData;

    public DetailsAdapter(ArrayList<Recepie> mData) {
        this.mData = mData;
    }

    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details, parent, false);
        DetailsAdapter.ViewHolder viewHolder = new DetailsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsAdapter.ViewHolder holder, int position) {
        Recepie recepie = mData.get(position);
        if (recepie.getThumbnail().toString().length() > 0) {
            Picasso.with(holder.itemView.getContext()).load(recepie.getThumbnail()).into(holder.imgView);
        }
        holder.txtViewTitleVal.setText(recepie.getTitle());
        holder.txtViewIngreVal.setText(recepie.getIngredients());
        holder.txtViewURLVal.setText(recepie.getHref());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitleVal;
        ImageView imgView;
        TextView txtViewURLVal;
        TextView txtViewIngreVal;
        public ViewHolder(final View itemView) {
            super(itemView);

            txtViewTitleVal = (TextView)itemView.findViewById(R.id.textViewTitleVal);
            txtViewIngreVal = (TextView)itemView.findViewById(R.id.txtViewIngreVal);
            txtViewURLVal = (TextView)itemView.findViewById(R.id.textViewURLVal);
            imgView = (ImageView) itemView.findViewById(R.id.imageRecepie);

            txtViewURLVal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(txtViewURLVal.getText().toString()));
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
