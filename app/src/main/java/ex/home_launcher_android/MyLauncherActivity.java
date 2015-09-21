package ex.home_launcher_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

public class MyLauncherActivity extends Activity  {
    RelativeLayout launcherLayout;
    GridLayout quickLaunchIcons;
    TextView tvDate;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_launcher);
        launcherLayout = (RelativeLayout) findViewById(R.id.launcherLayout);
        launcherLayout.setOnClickListener(ocl);
        tvDate= (TextView) findViewById(R.id.tvDate);
        quickLaunchIcons= (GridLayout) findViewById(R.id.quickLaunchIcons);

        for (int i = 0; i < quickLaunchIcons.getChildCount(); i++) {
            quickLaunchIcons.getChildAt(i).setOnClickListener(qlicl);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        date = new Date();
        tvDate.setText(date.toString().substring(0,11)+date.toString().substring(23,28));
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MyLauncherActivity.this,AllAppsActivity.class));
            overridePendingTransition(0,0);
        }
    };
    
    View.OnClickListener qlicl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // TODO: populate this area 07/09/2015
        }
    };






}
