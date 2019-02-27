package com.citrus.aboutcitrus;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.joaquimley.faboptions.FabOptions;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String LOG_TAG = "AboutCitrus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        boolean appPresent = appCheck("com.pluscubed.matlog");

                        if (appPresent) {
                            Snackbar.make(view, "Opening Logcat Reader", Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent("com.pluscubed.logcat.intents.LAUNCH");
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pluscubed.matlog"));
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

            final String listArray1[] = new String[]{
                    "Adarsh M.R",
                    "Rohit Poroli",
                    "Sathish Kumar",
                    "Aditya Garg",
                    "Satyabrat Sahoo",
                    "Rock Kellan",
                    "Minar (Recenz)"
            };

            final String listArray2[] = new String[]{
                    "Founder|Developer",
                    "Co-Founder|Developer",
                    "Developer",
                    "Developer",
                    "App Developer",
                    "Designer",
                    "Bootanimation Designer"
            };

            final String uriArray[] = new String[]{
                    "https://plus.google.com/+AdarshMR1",
                    "https://plus.google.com/+RohitPoroli",
                    "https://plus.google.com/+sathishkumar2026",
                    "https://plus.google.com/118340790645933812226",
                    "https://plus.google.com/104922572821409006545",
                    "",
                    "https://plus.google.com/+RecenzTastic"
            };

            int bgimageArray[] = new int[]{
                    R.drawable.adarsh_bg,
                    R.drawable.rohit_bg,
                    R.drawable.sathish_bg,
                    R.drawable.aditya_bg,
                    R.drawable.satyabrat_bg,
                    R.drawable.kellan_bg,
                    R.drawable.minar_bg
            };

            int imageArray[] = new int[]{
                    R.drawable.adarsh,
                    R.drawable.rohit,
                    R.drawable.sathish,
                    R.drawable.aditya,
                    R.drawable.satyabrat,
                    R.drawable.kellan,
                    R.drawable.minar
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

            final String listArray1[] = new String[]{
            };

            final String listArray2[] = new String[]{
            };

            final String uriArray[] = new String[]{
                    "https://plus.google.com/+AdarshMR1",
                    "https://plus.google.com/+RohitPoroli",
                    "https://plus.google.com/+sathishkumar2026",
                    "https://plus.google.com/118340790645933812226",
                    "https://plus.google.com/104922572821409006545",
                    "",
                    "https://plus.google.com/+RecenzTastic"
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
            return 2;
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
