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

package com.tune.news;

import android.app.Application;
import android.content.Context;

import com.tune.news.provider.DatabaseHelper;
import com.tune.news.provider.FeedDataContentProvider;
import com.tune.news.utils.PrefUtils;

public class MainApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        // init
        PrefUtils.putBoolean(PrefUtils.IS_REFRESHING, false);
        PrefUtils.putBoolean(PrefUtils.REFRESH_ENABLED, false);
        PrefUtils.putBoolean(PrefUtils.NOTIFICATIONS_ENABLED, false);
        PrefUtils.putBoolean(PrefUtils.REFRESH_ON_OPEN_ENABLED, true);

        try{
            if(PrefUtils.getInt(PrefUtils.APP_VERSION, 0) != BuildConfig.VERSION_CODE){
                mContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);

                FeedDataContentProvider.addFeed(this, "https://www.tinhte.vn/rss", "Tinh Tế", ".messageList .message:first-child blockquote", true);
                FeedDataContentProvider.addFeed(this, "http://vnexpress.net/rss/tin-moi-nhat.rss", "VN Express", "#article_content,.fck_detail,#main_player", true);
                FeedDataContentProvider.addFeed(this, "http://kenh14.vn/home.rss", "Kênh 14", ".knd-content,.content,.hehe-horo-comment", true);
                PrefUtils.putInt(PrefUtils.APP_VERSION, BuildConfig.VERSION_CODE);
            }
        }
        catch(Exception e){

        }
    }
}
