package net.fred.feedex.common;

/**
 * Created by MyPC on 19/06/2015.
 */
public interface Feeds {
    public interface TinhTe {
        public static final String NAME = "Tinh Te";
        public static final String URL = "https://www.tinhte.vn/rss";
        public static final String CSS_INDICATOR = ".messageList .message:first-child blockquote";
    }

    public interface Kenh14 {
        public static final String NAME = "Kenh 14";
        public static final String URL = "http://kenh14.vn/home.rss";
        public static final String CSS_INDICATOR = ".content,.hehe-horo-comment";
    }

    public interface VNExpress {
        public static final String NAME = "VNExpress";
        public static final String URL = "http://vnexpress.net/rss/tin-moi-nhat.rss";
        public static final String CSS_INDICATOR = ".fck_detail";
    }
}
