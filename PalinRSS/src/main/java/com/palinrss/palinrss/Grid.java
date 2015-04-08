package com.palinrss.palinrss;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Grid extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Log.v("RSS", "Ouverture de la grille");
        Intent myintent = getIntent();

        new StreamTask().execute(myintent.getStringExtra("URL"));
    }


    private class StreamTask extends AsyncTask<String, Void, InputStream>
    {


        @Override
        protected InputStream doInBackground(String... url) {

            try
            {
                URL StreamURL = new URL(url[0]);
                InputStream stream = StreamURL.openStream();
                return stream;
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(InputStream stream)
        {
            new GetXmlTask().execute(stream);
        }
    }

    private class GetXmlTask extends AsyncTask<InputStream, Void, List<XmlParser.Entry>>
    {

        @Override
        protected List<XmlParser.Entry> doInBackground(InputStream... params)
        {
            XmlParser parserxml = new XmlParser();
            try
            {
                if(params[0] != null)
                {
                    List<XmlParser.Entry> entriesList = parserxml.parse(params[0]);
                    return entriesList;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<XmlParser.Entry> entriesList)
        {
            ArrayList<ItemRSS> itemRSSList = new ArrayList<ItemRSS>();
            if (entriesList != null)
            {
                for (XmlParser.Entry entry : entriesList) {
                    ItemRSS itemRSS = new ItemRSS(entry.title, entry.description, entry.link);
                    itemRSSList.add(itemRSS);
                }

                ItemRSSAdapter adapter = new ItemRSSAdapter(Grid.this, itemRSSList);

                final ListView lView = (ListView) findViewById(R.id.listeArticles);
                lView.setAdapter(adapter);

                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View child, int position, long id) {
                        Intent intentNav = new Intent(Intent.ACTION_VIEW);
                        ItemRSS itemRSS = (ItemRSS) lView.getItemAtPosition(position);
                        intentNav.setData(Uri.parse(itemRSS.getLink()));
                        startActivity(intentNav);
                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "URL Invalide, aucun flux trouvé", Toast.LENGTH_LONG).show();
            }
        }
    }




}
