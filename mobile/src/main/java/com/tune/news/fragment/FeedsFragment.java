package com.tune.news.fragment;

import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tune.news.Constants;
import com.tune.news.R;
import com.tune.news.provider.FeedData;
import com.tune.news.utils.UiUtils;

import java.util.HashMap;
import java.util.Map;

public class FeedsFragment extends SwipeRefreshFragment {

    private ViewPager mFeedsViewPager;
    private FeedsViewPagerAdapter mFeedsViewPagerAdapter;
    private Map<Integer, ActionBarData> mActionBarDatas = new HashMap<Integer, ActionBarData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feeds, container, true);
        mFeedsViewPager = (ViewPager) rootView.findViewById(R.id.feedsViewPager);

        mFeedsViewPagerAdapter = new FeedsViewPagerAdapter(getActivity().getSupportFragmentManager());
        Cursor cursor = getActivity().getContentResolver().query(FeedData.FeedColumns.CONTENT_URI
                , new String[]{FeedData.FeedColumns._ID, FeedData.FeedColumns.NAME, FeedData.FeedColumns.ICON, FeedData.FeedColumns.PRIORITY}
                , null, null, null);
        if(cursor != null) {
            mFeedsViewPagerAdapter.setFeedsCount(cursor.getCount() + 1);
            while(cursor.moveToNext()){
                Integer feedPriority    = cursor.getInt(cursor.getColumnIndex(FeedData.FeedColumns.PRIORITY));
                Integer feedId          = cursor.getInt(cursor.getColumnIndex(FeedData.FeedColumns._ID));
                String actionBarTitle   = cursor.getString(cursor.getColumnIndex(FeedData.FeedColumns.NAME));
                byte[] bytes            = cursor.getBlob(cursor.getColumnIndex(FeedData.FeedColumns.ICON));
                Drawable actionBarIcon  = new BitmapDrawable(getResources(), UiUtils.getScaledBitmap(bytes, 24));
                mActionBarDatas.put(feedPriority, new ActionBarData(feedId, actionBarTitle, actionBarIcon));
            }
            cursor.close();
        }

        // Load AdMob
        AdView mAdView = (AdView) rootView.findViewById(R.id.adHomeView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("D8466940C49144F6336111363C348DCB").build();
        mAdView.loadAd(adRequest);

        // Add Favorites tab
        mActionBarDatas.put(0, new ActionBarData(-1, getString(R.string.favorites), ContextCompat.getDrawable(getActivity(), R.drawable.rating_important)));

        mFeedsViewPager.setAdapter(mFeedsViewPagerAdapter);
        mFeedsViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                ActionBarData actionBarData = mActionBarDatas.get(new Integer(position));
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(actionBarData.getTitle());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(actionBarData.getIcon());
            }
        });
        disableSwipe();
        return rootView;
    }

    @Override
    public void onRefresh() {

    }

    public class FeedsViewPagerAdapter extends FragmentPagerAdapter {

        public FeedsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private Integer feedsCount;
        public void setFeedsCount(Integer feedsCount) {
            this.feedsCount = feedsCount;
        }

        @Override
        public int getCount() {
            return feedsCount;
        }

        @Override
        public Fragment getItem(int position) {
            EntriesListFragment mEntriesFragment = new EntriesListFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.FEED_ID, mActionBarDatas.get(new Integer(position)).getFeedId());
            mEntriesFragment.setArguments(args);
            return mEntriesFragment;
        }
    }

    private class ActionBarData {
        private Integer feedId;
        private String title;
        private Drawable icon;

        public ActionBarData(Integer feedId, String title, Drawable icon) {
            this.feedId = feedId;
            this.title = title;
            this.icon = icon;
        }

        public Integer getFeedId() {
            return feedId;
        }

        public String getTitle() {
            return title;
        }

        public Drawable getIcon() {
            return icon;
        }
    }
}
