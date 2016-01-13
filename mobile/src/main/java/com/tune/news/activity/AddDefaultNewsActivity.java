/**
 * TuneNews
 * <p/>
 * Copyright (c) 2012-2015 Frederic Julian
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tune.news.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tune.news.R;
import com.tune.news.utils.UiUtils;

public class AddDefaultNewsActivity extends BaseActivity {

//    private static final int[] CB_IDS = new int[]{R.id.cb_tinhte, R.id.cb_vnexpress, R.id.cb_kenh14};
//    private static final int[] FEED_NAME = new int[]{R.string.default_news_tinhte, R.string.default_news_vnexpress, R.string.default_news_kenh14};
//    private static final int[] FEED_URL = new int[]{R.string.default_news_tinhte_url, R.string.default_news_vnexpress_url, R.string.default_news_kenh14_url};
//    private static final int[] FEED_CSS_INDICATOR = new int[]{R.string.default_news_tinhte_indicator, R.string.default_news_vnexpress_indicator, R.string.default_news_kenh14_indicator};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiUtils.setPreferenceTheme(this);
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_add_default_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

//    public void onClickOk(View view) {
//        for (int cb = 0; cb < CB_IDS.length; cb++) {
//            if (((CheckBox) findViewById(CB_IDS[cb])).isChecked()) {
//                FeedDataContentProvider.addFeed(this, getString(FEED_URL[cb]), getString(FEED_NAME[cb]), getString(FEED_CSS_INDICATOR[cb]), true);
//            }
//        }
//
//        setResult(RESULT_OK);
//        finish();
//    }

    public void onClickCancel(View view) {
        finish();
    }
}

