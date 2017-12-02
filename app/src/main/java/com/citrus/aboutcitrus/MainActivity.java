package com.citrus.aboutcitrus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.citrus.aboutcitrus.adapter.MaintainerAdapter;
import com.citrus.aboutcitrus.adapter.TCAdapter;
import com.citrus.aboutcitrus.helper.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.joaquimley.faboptions.FabOptions;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String TAG = "AboutCitrus";
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Notification");
                    alertDialog.setMessage(message);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        };

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FabOptions fabOptions = findViewById(R.id.fab_options);
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.faboptions_gplus: {
                        Snackbar.make(view, "Opening Citrus Google+ Community", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.citrus_url)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_tg: {
                        Snackbar.make(view, "Opening Telegram Channel for Citrus-CAF", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.citrus_channel)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_git: {
                        Snackbar.make(view, "Opening Citrus Source Github", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.citrus_github)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_log: {
                        boolean appPresent = appCheck("com.nolanlawson.logcat");

                        if (appPresent) {
                            Snackbar.make(view, "Opening Logcat Reader", Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent("com.nolanlawson.logcat.intents.LAUNCH");
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.nolanlawson.logcat"));
                            startActivity(i);
                        }

                    }
                    default:
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_wishbucket) {
            startActivity(new Intent(MainActivity.this, MailDialogActivity.class));
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

    //#1
    public static class AboutFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public AboutFragment() {
        }

        public static AboutFragment newInstance(int sectionNumber) {
            AboutFragment fragment = new AboutFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_about, container, false);
            return rootView;
        }
    }

    //#2
    public static class TCFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public TCFragment() {
        }

        public static TCFragment newInstance(int sectionNumber) {
            TCFragment fragment = new TCFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String listArray1[] = new String[]{
                    "Adarsh MR",
                    "Rohit Poroli",
                    "Satyabrat Sahoo",
                    "Aditya Garg",
                    "Rock Kellan"

            };

            String listArray2[] = new String[]{
                    "Founder/Developer",
                    "Co-Founder/Developer",
                    "App Developer",
                    "Developer",
                    "Designer"

            };

            final String uriArray[] = new String[]{
                    "https://plus.google.com/+AdarshMR1",
                    "https://plus.google.com/u/0/104922572821409006545",
                    "https://plus.google.com/109706543444382669418",
                    "https://plus.google.com/118340790645933812226",
                    ""
            };

            int bgimageArray[] = new int[]{
                    R.drawable.adarsh_bg,
                    R.drawable.rohit_bg,
                    R.drawable.satyabrat_bg,
                    R.drawable.aditya_bg,
                    R.drawable.kellan_bg
            };

            int imageArray[] = new int[]{
                    R.drawable.adarsh,
                    R.drawable.rohit,
                    R.drawable.satyabrat,
                    R.drawable.aditya,
                    R.drawable.kellan
            };

            RecyclerView recyclerView = rootView.findViewById(R.id.tcListView);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new TCAdapter(getActivity(), listArray1, listArray2, imageArray, bgimageArray, uriArray));
            recyclerView.setNestedScrollingEnabled(true);
            return rootView;
        }
    }

    //#3
    public static class MaintainerFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public MaintainerFragment() {
        }

        public static MaintainerFragment newInstance(int sectionNumber) {
            MaintainerFragment fragment = new MaintainerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String listArray1[] = new String[]{
                    "Aashish Toshniwal",
                    "Aditya Garg",
                    "Davide Garberi",
                    "Harsh Shandilya",
                    "Rahif",
                    "Rohit Poroli",
                    "Romil P",
            };

            String listArray2[] = new String[]{
                    "Lettuce",
                    "Oneplus3/Kenzo/Gemini/Hydrogen",
                    "Zuk z2/Lenovo Z2 Plus",
                    "Jalebi",
                    "wt88047",
                    "Mido",
                    "OnePlus One",
            };

            final String uriArray[] = new String[]{
                    "https://plus.google.com/+AdarshMR1",
                    "https://plus.google.com/u/0/104922572821409006545",
                    "https://plus.google.com/109706543444382669418",
                    "https://plus.google.com/118340790645933812226",
                    "",
                    "",
                    ""
            };

            RecyclerView recyclerView = rootView.findViewById(R.id.tcListView);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new MaintainerAdapter(getActivity(), listArray1, listArray2, uriArray));
            recyclerView.setNestedScrollingEnabled(true);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AboutFragment.newInstance(position + 1);
                case 1:
                    return TCFragment.newInstance(position + 1);
                case 2:
                    return MaintainerFragment.newInstance(position + 1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "About";
                case 1:
                    return "TeamCardinal";
                case 2:
                    return "Maintainers";
            }
            return null;
        }
    }
}
