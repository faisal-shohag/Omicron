package com.example.covidinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VariantsAdapdar extends ArrayAdapter {

    List<VariantsType> variantsInfo = new ArrayList<VariantsType>();


    public VariantsAdapdar(@NonNull Context context, List<VariantsType> variantsInfo ) {
        super(context, R.layout.list_view, variantsInfo );
        this.variantsInfo = variantsInfo;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_view, parent, false);

        ImageView variantsImg = row.findViewById(R.id.list_variants_image);
        TextView variantsName = row.findViewById(R.id.list_variants_name);
        TextView vSubText = row.findViewById(R.id.subtext);

        variantsImg.setImageResource(variantsInfo.get(position).getImgId());
        variantsName.setText(variantsInfo.get(position).getVariantsName());
        vSubText.setText(variantsInfo.get(position).getSubText());


        return row;
    }
}