package com.example.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView bdCases;
    private TextView bdDeath;
    private TextView bdRecovered;
    private TextView bdTodayCases;
    private TextView bdTodayDeaths;
    private RequestQueue mQueue;
    private RequestQueue headQ;
    private ProgressBar progressBar;
    private ImageView countryImage;
    private TextView countryName;
    private TextView headlines;
    private ListView variantListView;
    static List<VariantsType> dataList;
    private Button okay_button;
    private ImageView showAbout;
    String[] items = {"World","Bangladesh", "India", "Pakistan", "China", "Myanmar", "USA", "United Kingdom", "Russian Federation", "Italy", "Brazil"};
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAbout = (ImageView) findViewById(R.id.show_about);
        showAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAboutDialog();
            }
        });

        //Accessing UI
        progressBar = findViewById(R.id.main_prog);
        bdCases = findViewById(R.id.bd_cases);
        bdDeath = findViewById(R.id.bd_death);
        bdRecovered = findViewById(R.id.bd_recovered);
        bdTodayCases = findViewById(R.id.bd_today_cases);
        bdTodayDeaths = findViewById(R.id.bd_today_death);

        //Variants ListView
        variantListView = (ListView) findViewById(R.id.variants_list);
        dataList = new ArrayList<VariantsType>();
        //Adding data to datalist List
        dataList.add(new VariantsType(R.drawable.alpha, "Alpha", "United Kingdom, Sep 2020", "<b>Documented: </b>United Kingdom, Sep 2020 <br><b>Designated:  </b> 18 Dec 2020 <br><b>Pango lineage•: </b> B.1.1.7 <br><br>The Alpha variant, also known as lineage <b>B.1.1.7</b>, is a variant of <b>SARS-CoV-2</b>, the virus that causes COVID-19. One of several variants of concern, the variant is estimated to be 40–80% more transmissible than the wild-type <b>SARS-CoV-2</b><br><br><h4><font color='#F44336'><b> Symptoms</b></font></h4>Imperial College London investigated over a million people in England while the Alpha variant was dominant and discovered a wide range of further symptoms linked to Covid. <b>\"Chills, loss of appetite, headache and muscle aches\"</b> were most common in infected people, as well as classic symptoms.<br><br> <h4><font color='#FF9800'><b> Diagnosis</b></font></h4> Several rapid antigen tests for SARS-CoV-2 are in widespread use globally for COVID-19 diagnostics. They are believed to be useful in stopping the chain of transmission of the virus by providing the means to rapidly identify large numbers of cases as part of a mass-testing program. Following the emergence of VOC-202012/01, there was initially concern that rapid tests might not detect it, but Public Health England determined that rapid tests evaluated and used in the United Kingdom detect the variant.<br><br> <h4><font color='#4CAF50'><b> Prevention</b></font></h4>As of late 2020, several COVID-19 vaccines were being deployed or under development.However, as further mutations occur, concerns were raised as to whether vaccine development would need to be altered. SARS-CoV-2 does not mutate as quickly as, for example, influenza viruses, and the new vaccines that had proved effective by the end of 2020 are types that can be adjusted if necessary.[22] As of the end of 2020, German, British, and American health authorities and experts believe that existing vaccines will be as effective against VOC 202012/01 as against previous variants. <br></br></small><i><b>Sources:</b> Who, Wikipedia</i></small>"));
        dataList.add(new VariantsType(R.drawable.beta, "Beta", "South Africa, May 2020","<b>Documented:  </b> South Africa, May 2020 <br><b>Designated:  </b> 18 Dec 2020 <br><b>Pango lineage•: </b> B.1.351 <br><br>The Beta variant, also known as lineage B.1.351, is a variant of SARS-CoV-2, the virus that causes COVID-19. One of several SARS-CoV-2 variants believed to be of particular importance.<br><br><h4><font color='#F44336'><b> Symptoms</b></font></h4>There is no indication that symptoms of the beta variant are any different to other Covid variants. But as the number of cases have been small compared to other variants there have been no widespread studies. <br> The variant is believed to be more transmissible than the original Wuhan virus but it is not thought to lead to more severe disease. <br> Jonathan Ball, professor of molecular virology at Nottingham University, described the beta variant as a \"bit of a lightweight\" - and most experts agree it has been outpaced by the more dominant delta strain.<br><br> <h4><font color='#FF9800'><b> Diagnosis</b></font><br></h4> South Africa stopped using the AstraZeneca vaccine after a small study in February showed that it offered only limited protection against mild disease caused by the beta variant.<br> The study of just over 2,000 people by researchers at the University of Witwatersrand and University of Oxford showed that the jab was only 22 per cent effective at preventing mild to moderate disease. <br> Studies of the Pfizer and Moderna vaccine showed that they both were effective against the beta variant. A study of real-world data from Qatar, where beta was once widespread, showed that it was 72 to 75 per cent effective at preventing infection.  <br><br> <h4><font color='#4CAF50'><b> Prevention</b></font></h4>The vaccines seem to be less powerful against beta than against other versions of the virus. But studies suggest that two doses of several widely used vaccines should still offer strong protection.<br></br></small><i><b>Sources:</b> Who, Wikipedia, Telegraph, The Economic Times</i></small>"));
        dataList.add(new VariantsType(R.drawable.gamma, "Gamma", "Brazil, Nov 2020 ","<b>Documented:  </b> Brazil, Nov 2020 <br><b>Designated:  </b> 11 Jan 2021 <br><b>Pango lineage•: </b> P.1<br><br>The Gamma variant, also known as lineage P.1, is one of the variants of SARS-CoV-2, the virus that causes COVID-19. This variant of SARS-CoV-2 has been named lineage P.1 and has 17 amino acid substitutions.<br><br><h4><font color='#F44336'><b> Symptoms</b></font></h4>Same as other variants.<br><br> <h4><font color='#FF9800'><b> Diagnosis</b></font><br></h4> The current molecular tests detect most of the variants and thus are able to diagnose COVID-19 infection by such variants. Yet, the fine identification of the type of variants is still based on sequence analysis although multiplex PCR test are being evaluated. <br> <br><br> <h4><font color='#4CAF50'><b> Prevention</b></font></h4>This variant contains the potential immune escape mutation (E484K). The variant has shown to be relatively resistant to neutralization by convalescent plasma and vaccinee sera after Moderna or Pfizer vaccination. However, the magnitude of the loss was modest (3.8-4.8 fold).<br>The study was conducted among nearly 70,000 health care workers in Manaus, which was the epicenter for the emergence of the P.1 variant. CoronaVac (inactivated vaccine) was shown to be 50% effective in preventing illness 14 days after administration of the first dose in its two-dose schedule.<br><br></br></small><i><b>Sources:</b> Who, Wikipedia, Global Virus Network</i></small>"));
        dataList.add(new VariantsType(R.drawable.delta, "Delta", "India, Oct 2020", "<b>Documented:  </b> India, Oct 2020 <br><b>Designated:  </b> VOI: 04 Apr 2021, VOC: 11 May 2021 <br><b>Pango lineage•: </b> B.1.617.2<br><br>The Delta variant is a variant of SARS-CoV-2, the virus that causes COVID-19. It was first detected in India in late 2020. The Delta variant was named on 31 May 2021 and had spread to over 179 countries by 22 November 2021. <br> The variant could be more than twice as transmissible as the original strain of SARS-CoV-2.<br><br><h4><font color='#F44336'><b> Symptoms</b></font></h4>A recent study showed that the hamsters infected with B.1.617.1 demonstrated increased body weight loss, higher viral load in lungs and pronounced lung lesions as compared to B.1 variant. Further studies will be required to confirm its disease severity. <br>It is not clear whether this variant enhances the disease severity. <br><br> <h4><font color='#FF9800'><b> Diagnosis</b></font><br></h4> The current molecular tests detect most of the variants and thus are able to diagnose COVID-19 infection by such variants. Yet, the fine identification of the type of variants is still based on sequence analysis although multiplex PCR test are being evaluated. <br> <br><br> <h4><font color='#4CAF50'><b> Prevention</b></font></h4>Use of  a live virus assay showed that the B.1.617.1 variant is 6.8-fold more resistant to neutralization by sera from COVID-19 convalescent and Moderna and Pfizer vaccinated individuals.<br></br></small><i><b>Sources:</b> Who, Wikipedia, Global Virus Network</i></small>"));
        dataList.add(new VariantsType(R.drawable.omicron, "Omicron", "Multiple countries, Nov 2021", "<b>Documented:  </b> Multiple countries, Nov 2021 <br><b>Designated:  </b> VUM: 24 Nov 2021, VOC: 26 Nov 2021 <br><b>Pango lineage•: </b> B.1.1.529<br><br>The Omicron variant is a variant of SARS-CoV-2, the virus that causes COVID-19. The first known confirmed B.1.1.529 infection was from a specimen collected on 9 November 2021.<br>The number of cases of this variant appears to be increasing in almost all provinces in South Africa.<br>This variant has been detected at faster rates than previous surges in infection, suggesting that this variant may have a growth advantage. <br>High numbers of spike mutations (at least 32 mutations) raise concerns.<br> The variant shares similarities with the Lambda and Beta variants, which are associated with an innate evasion of immunity. <br><br><h4><font color='#F44336'><b> Symptoms</b></font></h4>No certain information found yet. <br><br> <h4><font color='#FF9800'><b> Diagnosis</b></font><br></h4> The current molecular tests detect most of the variants and thus are able to diagnose COVID-19 infection by such variants. Yet, the fine identification of the type of variants is still based on sequence analysis although multiplex PCR test are being evaluated. <br> <br><br> <h4><font color='#4CAF50'><b> Prevention</b></font></h4>No certain information found yet. <br><br></br></small><i><b>Sources:</b> Who, Wikipedia, Global Virus Network</i></small>"));
        //List view adaptar which will set the variants to the UI ListView.
        VariantsAdapdar vadaptar = new VariantsAdapdar(getApplicationContext(), dataList);
        variantListView.setAdapter(vadaptar);
        //Click event to each Variants that will navigate us to a new activity
        variantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), variantsDetails.class);
                intent.putExtra("variant", position);
                startActivity(intent);
            }
        });


        headlines = findViewById(R.id.headlines);
        headlines.setSelected(true);
        mQueue = Volley.newRequestQueue(this);
        headQ = Volley.newRequestQueue(this);


        //Getting data from API
        getData("bangladesh");
        getHeadlines();
        countryImage = (ImageView) findViewById(R.id.flag);
        countryName = (TextView) findViewById(R.id.country_name);
        Spinner spinner = (Spinner) findViewById(R.id.countries_drop_down);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                countryName.setText(item);
                String country = item.toLowerCase();
                System.out.println(country);
                if(country.equals("world")) countryImage.setImageResource(R.mipmap.world);
                else Picasso.get().load("https://countryflagsapi.com/png/"+country).into(countryImage);
                progressBar.setVisibility(View.VISIBLE);

                if(country.equals("russian federation")){
                    getData("russia");
                }else if(country.equals("united kingdom")){
                    getData("uk");
                }else getData(country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //About Dialog
    private void showAboutDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("This app was build for OOP Course Project.\nTeam: Faisal, Shihab, Mushfiq, Rakib, Kajal\nCSE, BRUR\nRelease Date: 02 Jan 2021");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    //Getting covid cases, deaths, recover data from api
    private void getData(String countryName){
        String url = "https://coronavirus-19-api.herokuapp.com/countries/"+countryName;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Found");
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    bdTodayCases.setText(jsonObject.getString("todayCases"));
                    bdTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    bdCases.setText(jsonObject.getString("cases"));
                    bdDeath.setText(jsonObject.getString("deaths"));
                    bdRecovered.setText(jsonObject.getString("recovered"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
      mQueue.add(stringRequest);
    }

//    Getting headlines from online newspaper source code
    private void getHeadlines(){
        String url = "https://api.allorigins.win/get?url=https://bdnews24.com/coronavirus-pandemic/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Found!");
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String headline = jsonObject.getString("contents");

                    String h[] = headline.split("\n");

                    String finalHeadlines = "";
                    for(int i=3785; i<3854; i++){
                        if(h[i].contains("<a")){
                            String a[] = h[i].split(">");
                            a = a[1].split("<");
                            String b = a[0].replaceAll("&#039;", "'");
                            finalHeadlines += b + " ★ ";
                        }
                    }
                    headlines.setText(finalHeadlines);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        headQ.add(stringRequest);
    }
}