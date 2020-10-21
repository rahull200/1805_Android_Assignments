package codebind.example.assign10_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.mywebview);
    //    String cont = "<html><body>Hello</body></html>";

        if(isNetworkAvailable()){
            //Enable javascript
            webView.getSettings().setJavaScriptEnabled(true);

            //Enable location
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);

            //To open the website and not the youtube app
            webView.setWebViewClient(new WebViewClient());

            webView.loadUrl("https://www.youtube.com/");
        }else{

            //Enable javascript
            webView.getSettings().setJavaScriptEnabled(true);

            //Enable location
            webView.getSettings().setGeolocationEnabled(true);

            webView.loadUrl("file:///android_asset/profile.html");

        }

        //   webView.loadUrl("file:///android_asset/profile.html");

      //  webView.loadData(cont,"text/html","UTF-8");

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}