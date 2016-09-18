package develop.beta1139.investigateproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startUpdateNewsService();
    }

    private void startUpdateNewsService() {
        Log.e("dbg", Thread.currentThread().getStackTrace()[2].getMethodName() + " called!!");

        Intent intent = new Intent(this, UpdateNewsService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                this, -1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        long targetMillis = UpdateNewsService.getTargetMillis();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetMillis, AlarmManager.INTERVAL_DAY,
                pendingIntent);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetMillis, 10 * 1000, pendingIntent);
    }
}

