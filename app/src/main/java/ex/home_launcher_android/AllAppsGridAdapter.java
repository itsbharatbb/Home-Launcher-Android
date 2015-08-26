package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AllAppsGridAdapter extends BaseAdapter {
    IconCachingClass cachingClass;
    Context context;
    AllAppsGridAdapter(Context context){
        this.context=context;
        cachingClass = new IconCachingClass(context);
        cachingClass.cacheIcons();
    }
    @Override
    public int getCount() {
       return cachingClass.appsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cachingClass.appsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.single_grid_item,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.icon=(ImageView) convertView.findViewById(R.id.appIcon);
            viewHolder.label=(TextView) convertView.findViewById(R.id.appLabel);
            viewHolder.intent = new Intent();
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }


//        viewHolder.icon.setImageBitmap(BitmapFactory.decodeFile(context.getFilesDir() + "/" + cachingClass.appsList.get(position).activityInfo.packageName));
        viewHolder.label.setText("App");
        viewHolder.intent.setClassName(cachingClass.appsList.get(position).
                activityInfo.packageName, cachingClass.appsList.get(position).activityInfo.name);
        try {
            viewHolder.icon.setImageDrawable(context.getPackageManager().getActivityIcon(viewHolder.intent));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return convertView;

    }

    protected static class ViewHolder {
        TextView label;
        ImageView icon;
        Intent intent;
    }
}
