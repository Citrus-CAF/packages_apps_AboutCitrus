package com.citrus.aboutcitrus;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //#1
    public static class AboutFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private final String LOG_TAG = AboutFragment.class.getSimpleName();

        private ArrayAdapter<String> listAdapter;

        public AboutFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private final String LOG_TAG = TCFragment.class.getSimpleName();

        private ArrayAdapter<String> listAdapter;

        public TCFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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

            String listArray1[] = new String[] {
                    "Adarsh MR",
                    "Rohit Poroli"
            };

            String listArray2[] = new String[] {
                    "Founder/Developer",
                    "Co-Founder/Developer"
            };

            int bgimageArray[] = new int[] {
                    R.drawable.dev1_bg,
                    R.drawable.dev2_bg
            };

            int imageArray[] = new int[] {
                    R.drawable.dev1,
                    R.drawable.dev2
            };

            int pos = getArguments().getInt(ARG_SECTION_NUMBER);
            Log.d(LOG_TAG, Integer.toString(pos));

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new TCAdapter(getActivity() , listArray1, listArray2, bgimageArray, imageArray));
            Log.d(LOG_TAG,"DONE");
            return rootView;
        }
    }

    //#3
    public static class MaintainerFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private final String LOG_TAG = MaintainerFragment.class.getSimpleName();

        private ArrayAdapter<String> listAdapter;

        public MaintainerFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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

            String listArray1[] = new String[] {
                    "L",
                    "O",
                    "L",
                    "DO",
                    "K",
                    "F",
                    "G"
            };

            String listArray2[] = new String[] {
                    "L",
                    "O",
                    "L",
                    "DO",
                    "K",
                    "F",
                    "G"
            };

            int pos = getArguments().getInt(ARG_SECTION_NUMBER);
            Log.d(LOG_TAG, Integer.toString(pos));

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new MaintainerAdapter(getActivity() , listArray1, listArray2));
            Log.d(LOG_TAG,"DONE");
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
