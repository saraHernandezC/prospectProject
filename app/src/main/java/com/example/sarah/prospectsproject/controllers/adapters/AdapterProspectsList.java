package com.example.sarah.prospectsproject.controllers.adapters;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.GetImageView;

import java.util.List;

/**
 * Created by sarah on 12/09/2018.
 */

public class AdapterProspectsList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private final Context context;
    private List<Prospect> prospectList;
    private final IProspectListView iProspectListView;
    private final GetImageView getImageView;

    public AdapterProspectsList(Context context, IProspectListView iProspectListView, List<Prospect> prospectList) {
        this.context = context;
        this.prospectList = prospectList;
        this.iProspectListView = iProspectListView;
        getImageView = new GetImageView();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_prospect, parent, false);
        return new CustomViewHolder(view);
    }

    public void addItemOrUpdateItem(List<Prospect> respuestaPersonas) {
        prospectList = respuestaPersonas;
        notifyDataSetChanged();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewIdentification;
        private final TextView textViewTelephone;
        private final ImageView imageViewEstate;
        private final LinearLayout linearLayoutProspect;

        private final CardView cardViewArticle;

        CustomViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.textView_name);
            textViewIdentification = view.findViewById(R.id.textView_identification);
            textViewTelephone = view.findViewById(R.id.textView_telephone);
            imageViewEstate = view.findViewById(R.id.imageView_status);

            linearLayoutProspect = view.findViewById(R.id.linearLayoutProspect);
            cardViewArticle = view.findViewById(R.id.cardView_prospect);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        final Prospect prospect = prospectList.get(position);
        String prospectFullName = prospect.getName().concat(Constants.SPACE.concat(prospect.getSurname())) ;
        customViewHolder.textViewName.setText(prospectFullName);
        customViewHolder.textViewIdentification.setText(prospect.getSchProspectIdentification());
        customViewHolder.textViewTelephone.setText(prospect.getTelephone());
        customViewHolder.imageViewEstate.setImageResource(getImageView.getImageStateView(prospect.getStatusCd()));
        customViewHolder.cardViewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iProspectListView.inflateProspectInformationLayout(prospect);
                }
        });
        customViewHolder.cardViewArticle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                iProspectListView.longClick(prospect);
                  return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return prospectList.size();
    }
}
