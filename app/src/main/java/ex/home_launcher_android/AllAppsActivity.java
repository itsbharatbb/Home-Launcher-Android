package ex.home_launcher_android;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAppsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks{

    GridView gridView;
    AllAppsGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();

    }

    private void activityInit() {
        setContentView(R.layout.activity_all_apps);
        gridView = (GridView) findViewById(R.id.allAppsGrid);
        adapter = new AllAppsGridAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(oicl);
    }

    AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity((Intent) adapter.getItem(position));
//            overridePendingTransition(0,0);
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

        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
//TODO:Complete this
            case R.id.action_most_recent:
            case R.id.action_most_used:
            case R.id.action_alphabetical:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    class updateAdapterTask extends AsyncTask<String, Object, Object> {
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


    public class AllAppsGridAdapter extends BaseAdapter {
        //        ObjectOutputStream oos;
//        FileOutputStream fos;
//        File file1;
        Context context;
        Map intentMap;
        Map titleMap;
        List<ResolveInfo> l;
        ModelClass model;
        Gson gson;
        Map drawableMap;

        AllAppsGridAdapter(Context context) {
            intentMap = new HashMap<>();
            titleMap = new HashMap<>();
            drawableMap = new HashMap<>();
            model = new ModelClass(context);
            gson = new Gson();
            this.context = context;
            SharedPreferences prefs = context.getSharedPreferences("SETTINGS",Context.MODE_PRIVATE);
            intentMap =gson.fromJson(prefs.getString("intentMap",null),intentMap.getClass());
            titleMap = gson.fromJson(prefs.getString("titleMap",null),titleMap.getClass());
            try {
                File file = new File(context.getFilesDir(),"drawableMap.ser");
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                drawableMap = (Map) ois.readObject();
                ois.close();
                fis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void updateAdapter() {
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return intentMap.size();
        }

        @Override
        public Object getItem(int position) {
            return intentMap.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.single_grid_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.appIcon);
                viewHolder.label = (TextView) convertView.findViewById(R.id.appLabel);
                viewHolder.intent = new Intent();
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.intent = (Intent) getItem(position);
            viewHolder.label.setText((String) titleMap.get(viewHolder.intent));
            viewHolder.icon.setImageDrawable((Drawable)drawableMap.get(viewHolder.intent));

            return convertView;

        }


    }

    protected static class ViewHolder {
        TextView label;
        ImageView icon;
        Intent intent;
        Date installedDate;
        int openCounts;
    }


}
