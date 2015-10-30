package com.intel.inde.moe.samples.webbrowser.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.intel.inde.moe.samples.webbrowser.common.Bookmarks;

import intel.com.webbrowser.R;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText webAddress;

    private void loadUrl(String string) {
        string = Bookmarks.prepareUrlAddress(string);
        webAddress.setText(string);
        webView.loadUrl(Bookmarks.prepareUrlAddress(string));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        webAddress = (EditText)findViewById(R.id.webAddress);
        webView.setWebViewClient(new WebViewClient());

        if (webAddress.getText().toString().isEmpty()) {
            loadUrl(Bookmarks.bookmarks[0]);
        } else {
            loadUrl(webAddress.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bookmarks) {

            Intent intent = new Intent(this, BookmarksActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String address = data.getStringExtra("address");
        loadUrl(address);
    }
}
