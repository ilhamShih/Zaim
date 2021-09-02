package com.zaimutest777.zaim.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zaimutest777.zaim.R;
import com.squareup.picasso.Picasso;
import com.zaimutest777.zaim.interfaces.RecyclerItemClickListener;
import com.zaimutest777.zaim.model.MapNavigatorList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilham Shihnazarow  on 9/7/2021
 */

public class GeneralFragmentsAdapter extends RecyclerView.Adapter<GeneralFragmentsAdapter.MarsPhotosViewHolder>{

    private ArrayList<MapNavigatorList> mapNavigatorListArrayList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private List<MapNavigatorList> productListFiltered;

    public GeneralFragmentsAdapter(ArrayList<MapNavigatorList> mapNavigatorListArrayList, RecyclerItemClickListener recyclerItemClickListener ){

        this.mapNavigatorListArrayList = mapNavigatorListArrayList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.productListFiltered = mapNavigatorListArrayList;
    }

    @Override
    public MarsPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_mars_photo, parent, false);
        return new MarsPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MarsPhotosViewHolder holder, int position) {
         MapNavigatorList product = mapNavigatorListArrayList.get(position);
         product   = productListFiltered.get(mapNavigatorListArrayList.size() - 1);

        holder.title.setText(product.getTitle());
        holder.summText.setText(product.getSummText());
        holder.betText.setText(product.getBetText());
        String description = product.getDescription();

        String score = product.getScore();
        String mastercard = product.getMastercard();
        String mir = product.getMir();
        String visa = product.getVisa();
        String qiwi = product.getQiwi();
        String yandex = product.getYandex();
        String cash = product.getCash();

        if(mastercard.equals("1")){ holder.mastercard.setVisibility(View.VISIBLE); }
        if(mir.equals("1")){ holder.mir.setVisibility(View.VISIBLE); }
        if(visa.equals("1")){ holder.visa.setVisibility(View.VISIBLE); }
        if(qiwi.equals("1")){ holder.qiwi.setVisibility(View.VISIBLE); }
        if(yandex.equals("1")){ holder.yandex.setVisibility(View.VISIBLE); }
        if(cash.equals("1")){ holder.cash.setVisibility(View.VISIBLE); }


        holder.ratingBar.setRating(Integer.parseInt(score));
        String url = product.getUrl();
        final String logoPhotoURL = product.getImageUrl();
        String titles =product.getTitle();





        Picasso.get()
                .load(logoPhotoURL)
                .into(holder.imageUrl);


        holder.buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemClickListener.onItemClick(url);
            }
        });

        holder.detalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemClickListener.onItem2Click(description, url, logoPhotoURL, titles);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(productListFiltered != null)
            return productListFiltered.size()-productListFiltered.size()+1;
        else
            return 0;
    }

    class MarsPhotosViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title, summText, betText, detalls;
        ImageView imageUrl, mastercard, mir, visa, qiwi, yandex, cash;
        Button buttom;
        RatingBar ratingBar;

        MarsPhotosViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.cardView);
            title = view.findViewById(R.id.title);
            summText = view.findViewById(R.id.summText);
            betText = view.findViewById(R.id.betText);
            imageUrl = view.findViewById(R.id.imageUrl);
            buttom = view.findViewById(R.id.buttom);
            detalls = view.findViewById(R.id.detalls);
            ratingBar = view.findViewById(R.id.ratingBar);

            mastercard = view.findViewById(R.id.mastercard);
            mir = view.findViewById(R.id.mir);
            visa = view.findViewById(R.id.visa);
            qiwi = view.findViewById(R.id.qiwi);
            yandex = view.findViewById(R.id.yandex);
            cash = view.findViewById(R.id.cash);
        }
    }

}
