package ex.home_launcher_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class AllAppsGridAdapter extends BaseAdapter {
    IconCachingClass cachingClass;
    Context context;
    AllAppsGridAdapter(Context context){
        this.context=context;
        cachingClass = new IconCachingClass(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        ImageButton imageButton;
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.single_grid_item,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.icon=(ImageButton) convertView.findViewById(R.id.appIcon);
            viewHolder.label=(TextView) convertView.findViewById(R.id.appLabel);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ib_browser, null));
        viewHolder.label.setText("App");
        return convertView;
    }

    private static class ViewHolder {
        TextView label;
        ImageButton icon;
    }
}
