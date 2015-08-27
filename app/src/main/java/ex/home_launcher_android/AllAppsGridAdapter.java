package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class AllAppsGridAdapter extends BaseAdapter {
    Context context;
    HashMap<Integer,Intent> d;
    List<ResolveInfo> l;
    ModelClass model;

    AllAppsGridAdapter(Context context, LauncherIntentList li){
        this.context=context;
        d=li.getIntentList();
        l=context.getPackageManager().queryIntentActivities(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0);
         model = new ModelClass(context);
    }

    void updateAdapter(){

        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
       return d.size();
    }

    @Override
    public Object getItem(int position) {
        return d.get(position);
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
//        viewHolder.intent.setClassName(cachingClass.appsList.get(position).
//                activityInfo.packageName, cachingClass.appsList.get(position).activityInfo.name);
        viewHolder.intent=model.intentMap.get(position);
        viewHolder.label.setText(model.titleMap.get(position));
        viewHolder.icon.setImageDrawable(model.drawableMap.get(position));

        return convertView;

    }

    protected static class ViewHolder {
        TextView label;
        ImageView icon;
        Intent intent;
    }
}
