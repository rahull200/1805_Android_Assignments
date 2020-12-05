package codebind.example.a1805_isa2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import codebind.example.a1805_isa2.Data.CharacterData;
import codebind.example.a1805_isa2.recyclerdata.Layout;
import codebind.example.a1805_isa2.recyclerdata.LayoutAdapter;
import codebind.example.a1805_isa2.recyclerdata.Season;

public class MainActivity extends AppCompatActivity {
    String fetchURL;
    RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Layout> layout;
    ArrayList<Season> seasons;
    static int totalseasons=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Breaking Bad Series");

        fetchURL ="https://www.breakingbadapi.com/api/characters?limit=10";

        doTask();


    }



    private void doTask() {
        if(isNetworkAvailable()){
            //Get data from server
            CharacterData data = new CharacterData();
            if(data.getCharacterData().size()==0){
                getData postsManager = new getData(this);
                postsManager.execute(fetchURL);
            }else{

                getData datamanager = new getData(this);
                try {
                    datamanager.setSeasons();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to leave the quiz ?");

        //Exit button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    class getData extends AsyncTask<String,Void,String> {
        Context ctx;
        JSONArray posts;
        //  private JSONArray posts;
        public getData(Context ctx) {
            this.ctx=ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String post_url=params[0];

            try{
                URL url=new URL(post_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                //   StringBuilder response= new StringBuilder();
                String line;
                StringBuffer stringBuffer =new StringBuffer("");
                while ((line = bufferedReader.readLine()) != null) {
                    //       response.append(line);
                    stringBuffer.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                posts = new JSONArray(result);
                addDataToLayout();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                setSeasons();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //Set seasons
        private void setSeasons() throws JSONException {
            seasons = new ArrayList<>();
            //Get season count
            if(totalseasons==0){
                totalseasons = getSeasonCount();
            }

            for(int i=1;i<=totalseasons;i++){
                seasons.add(new Season(i));
            }

            recyclerView = (RecyclerView)findViewById(R.id.resview);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ctx);
            resadapter = new LayoutAdapter(seasons,getApplicationContext());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(resadapter);
        }

        //Get total number of seasons
        private int getSeasonCount() throws JSONException {
            int maxseasoncount = 0;
            for(int i = 0;i<posts.length();i++) {
                JSONObject serverpost = posts.getJSONObject(i);
                String appearance = serverpost.getString("appearance");
                ArrayList<String> app = new ArrayList<>(Arrays.asList(appearance.split(",")));
                if(app.size()>maxseasoncount){
                    maxseasoncount = app.size();
                }
            }
            return maxseasoncount;
        }

        private void addDataToLayout() throws JSONException {
            layout = new ArrayList<>();

            for(int i = 0;i<posts.length();i++){
                JSONObject serverpost = posts.getJSONObject(i);

            //    Adding server data to layout
                layout.add(new Layout(serverpost.getString("name"),serverpost.getString("img"),
                        serverpost.getString("nickname"),serverpost.getString("appearance"),
                        serverpost.getString("portrayed")));

            }
            CharacterData characterData = new CharacterData();
            characterData.setCharacterData(layout);

        }

    }

}