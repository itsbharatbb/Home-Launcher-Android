package ex.home_launcher_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class AllAppsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apps);
        GridView gridView = (GridView) findViewById(R.id.allAppsGrid);
        AllAppsGridAdapter adapter = new AllAppsGridAdapter(this);
        gridView.setAdapter(adapter);

    }


}
