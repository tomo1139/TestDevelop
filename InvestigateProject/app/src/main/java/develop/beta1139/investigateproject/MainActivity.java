package develop.beta1139.investigateproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String NEWS_CAMPAIGN_URL = "http://animemap.net/api/table/tokyo.json";
    private JsonLoaderManager mJsonLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonLoaderManager = new JsonLoaderManager(this, NEWS_CAMPAIGN_URL);

        setupGetJsonButton();
        setupStartServiceButton();

    }

    private void setupStartServiceButton() {
        Button button = (Button) findViewById(R.id.startServiceButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUpdateNewsService();
            }
        });
    }

    private void setupGetJsonButton() {
        Button button = (Button) findViewById(R.id.getJsonButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(1, null, mJsonLoaderManager);
            }
        });
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

