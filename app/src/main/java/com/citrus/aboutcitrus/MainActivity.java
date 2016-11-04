package com.citrus.aboutcitrus;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Citrus Source Github", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.citrus_github)));
                startActivity(intent);
            }
        });
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
        if (id == R.id.action_channel) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.citrus_channel)));
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_wishbucket) {
            String addresses[] = {"cool.leo.aditya@gmail.com", "aranha.joshwin@gmail.com", "satyabrat.me@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "Adarshmr1998@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Citrus-CAF: WishBucket");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Wish");
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        }

        if (id == R.id.action_bugreport) {
            boolean appPresent = appCheck("com.nolanlawson.logcat");

            if (appPresent) {
                Intent intent = new Intent("com.nolanlawson.logcat.intents.LAUNCH");
                startActivity(intent);
            } else {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.nolanlawson.logcat"));
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean appCheck(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_present;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_present = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_present = false;
        }
        return app_present;
    }

    public void launchCitrus(View view) {
        Snackbar.make(view, "Opening Citrus Google+ Community", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.citrus_url)));
        startActivity(intent);
    }

    public void launchAdarshmr(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.adarshmr_url)));
        startActivity(intent);
    }

    public void launchRohit(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.rohit_url)));
        startActivity(intent);
    }
}
