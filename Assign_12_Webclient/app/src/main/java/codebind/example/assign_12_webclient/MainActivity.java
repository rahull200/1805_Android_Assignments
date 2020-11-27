package codebind.example.assign_12_webclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import codebind.example.assign_12_webclient.recyclerdata.Layout;
import codebind.example.assign_12_webclient.recyclerdata.LayoutAdapter;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Layout> layout;
    String fetchURL;
    static Integer salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = new ArrayList<>();

        fetchURL ="https://api.jsonbin.io/b/5f98044c30aaa01ce619982d";

        //Get data from server
        getPosts postsManager = new getPosts(this);
        postsManager.execute(fetchURL);




    }



    class getPosts extends AsyncTask<String,Void,String>{
        Context ctx;
        JSONArray posts;
        //  private JSONArray posts;
        public getPosts(Context ctx) {
            this.ctx=ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String post_url=params[0];
           // String post_url="https://wirmon.in/Android/posts/main_page.php";

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
            String title;
            JSONArray p;

            try {

                posts = new JSONArray(result);
                setContent(posts);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        public void setContent(JSONArray posts) throws JSONException {
            salary = 0;
            ArrayList<Layout> layout = new ArrayList<>();
            if(posts.length()==0){
                Toast.makeText(ctx,"No posts yet",Toast.LENGTH_LONG).show();

            }

            //Sort Json Array by lastName
            JSONArray sorteddata = new JSONArray();
            sorteddata = sortJsonData(posts);

            for(int i = 0;i<sorteddata.length();i++){
                JSONObject post = sorteddata.getJSONObject(i);

                layout.add(new Layout(post.getString("firstName"),post.getString("lastName"),post.getInt("salary"),post.getInt("empCode")));
                salary += post.getInt("salary");
            }

            recyclerView = (RecyclerView)findViewById(R.id.resview);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ctx);
            resadapter = new LayoutAdapter(layout);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(resadapter);


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fbtn);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Total salary: Rs "+salary,Toast.LENGTH_SHORT).show();
                }
            });
        }

        private JSONArray sortJsonData(JSONArray posts) throws JSONException {

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            JSONArray sorteddata = new JSONArray();

            for (int i = 0; i < posts.length(); i++) {
                jsonValues.add(posts.getJSONObject(i));
            }

            Collections.sort( jsonValues, new Comparator<JSONObject>() {
                //You can change "Name" with "ID" if you want to sort by ID
                private static final String KEY_NAME = "lastName";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    }
                    catch (JSONException e) {
                        //do something
                    }

                    return valA.compareTo(valB);
                    //if you want to change the sort order, simply use the following:
                    //return -valA.compareTo(valB);
                }
            });

            for (int i = 0; i < posts.length(); i++) {
                sorteddata.put(jsonValues.get(i));
            }
            return sorteddata;
        }
    }
}