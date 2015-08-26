package ex.home_launcher_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class AllAppsActivity extends Activity {

    AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(((AllAppsGridAdapter.ViewHolder)view.getTag()).intent);
                    }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apps);
        GridView gridView = (GridView) findViewById(R.id.allAppsGrid);
        AllAppsGridAdapter adapter = new AllAppsGridAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(oicl);

    }


}
