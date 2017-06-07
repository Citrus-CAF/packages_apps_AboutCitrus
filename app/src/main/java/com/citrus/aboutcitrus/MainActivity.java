package com.citrus.aboutcitrus;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.joaquimley.faboptions.FabOptions;
//import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
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

        if (id == R.id.action_wishbucket) {
            String addresses[] = {"cool.leo.aditya@gmail.com", "rohitp4all@gmail.com", "satyabrat.me@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "Adarshmr1998@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Citrus-CAF: WishBucket");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Wish");
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
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

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
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
                    "Aditya Garg"

            };

            String listArray2[] = new String[]{
                    "Founder/Developer",
                    "Co-Founder/Developer",
                    "App Developer",
                    "Developer"

            };

            int bgimageArray[] = new int[]{
                    R.drawable.adarsh_bg,
                    R.drawable.rohit_bg,
                    R.drawable.satyabrat_bg,
                    R.drawable.aditya_bg
            };

            int imageArray[] = new int[]{
                    R.drawable.adarsh,
                    R.drawable.rohit,
                    R.drawable.satyabrat,
                    R.drawable.aditya
            };

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new TCAdapter(getActivity(), listArray1, listArray2, bgimageArray, imageArray));
            listView.setNestedScrollingEnabled(true);
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
                    "Amardeep singh arora",
                    "Davide Garberi",
                    "Hamza Badar",
                    "Harsh Shandilya",
                    "Rahif",
                    "Rakesh Batra",
                    "Rohit Poroli",
                    "Subham Padhi",
                    "Vedant Kamalshekar"
            };

            String listArray2[] = new String[]{
                    "Lettuce",
                    "Oneplus3/Kenzo",
                    "OnePlus One",
                    "Zuk z2/Lenovo Z2 Plus",
                    "Otus",
                    "Jalebi",
                    "wt88047",
                    "Tomato",
                    "Onyx",
                    "OnePlus2",
                    "Armani"
            };

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new MaintainerAdapter(getActivity(), listArray1, listArray2));
            listView.setNestedScrollingEnabled(true);
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
            // Show 3 total pages.
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
