package ex.home_launcher_android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class AllAppsActivity extends Activity {

    GridView gridView;
    AllAppsGridAdapter adapter;
    LauncherIntentList li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apps);
        gridView = (GridView) findViewById(R.id.allAppsGrid);
        li=new LauncherIntentList(this);
        adapter = new AllAppsGridAdapter(this,li);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(oicl);

    }

    AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(((AllAppsGridAdapter.ViewHolder)view.getTag()).intent);
            overridePendingTransition(0,0);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_apps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.action_settings: startActivity(new Intent(this,SettingsActivity.class));
                break;
//TODO:Complete this
            case R.id.action_most_recent:
            case R.id.action_most_used:
            case R.id.action_alphabetical:
        }

        return super.onOptionsItemSelected(item);
    }

    class updateAdapterTask extends AsyncTask<String,Object,Object>{
        //TODO : Completre this
        @Override
        protected void onPostExecute(Object o) {
            adapter.updateAdapter();
        }

        @Override
        protected Object doInBackground(String... params) {
            return null;
        }
    }



}
