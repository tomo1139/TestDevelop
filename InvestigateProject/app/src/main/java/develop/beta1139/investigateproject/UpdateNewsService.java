package develop.beta1139.investigateproject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by tomo on 16/09/18.
 */
public class UpdateNewsService extends IntentService {

    //private static final int UPDATE_NEWS_HOUR = 6;

    private static final int UPDATE_NEWS_HOUR = 6;

    public UpdateNewsService() {
        super("UpdateNewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("dbg", Thread.currentThread().getStackTrace()[2].getMethodName() + " called!!" + ", Date: " + Util.getNowDate());
    }

    public static long getTargetMillis() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.setTimeZone(timeZone);
        calendarTarget.set(Calendar.HOUR_OF_DAY, UPDATE_NEWS_HOUR);
        calendarTarget.set(Calendar.MINUTE, 0);
        calendarTarget.set(Calendar.SECOND, 0);

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeZone(timeZone);

        long targetMillis = calendarTarget.getTimeInMillis();
        long nowMillis = calendarNow.getTimeInMillis();

        if (targetMillis < nowMillis) {
            calendarTarget.add(Calendar.DAY_OF_MONTH, 1);
            targetMillis = calendarTarget.getTimeInMillis();
        }

        return targetMillis;
    }
}
