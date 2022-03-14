package com.example.covidinfo;

import static com.example.covidinfo.MainActivity.dataList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class variantsDetails extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variants_details);
        int position = getIntent().getExtras().getInt("variant");

        ImageView variantImg = (ImageView) findViewById(R.id.variants_detalis_img);
        ImageView variantActionBarImg = (ImageView) findViewById(R.id.varinat_actionbar_image);
        TextView variantName = (TextView) findViewById(R.id.variants_detalis_name);
        TextView variantDetailsText = (TextView) findViewById(R.id.variant_details_txt);

        variantImg.setImageResource(dataList.get(position).getImgId());
        variantActionBarImg.setImageResource(dataList.get(position).getImgId());
        variantName.setText(dataList.get(position).getVariantsName());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) variantDetailsText.setText(Html.fromHtml(dataList.get(position).getVariantDetails(), Html.FROM_HTML_MODE_COMPACT));
        else variantDetailsText.setText(Html.fromHtml(dataList.get(position).getVariantDetails()));
    }
}