package org.es.koishi.navDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.es.koishi.mycar.R;

import java.util.List;

/**
 * Created by enrique.perez on 14/01/2016.
 */
public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {


    public DrawerListAdapter(Context context, List<DrawerItem> newList) {
        super(context, 0, newList);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        //If no convertView is present, we need to inflate it from the XML file for drawer item layout
        if (convertView == null) {
            LayoutInflater myInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = myInflater.inflate(R.layout.drawer_item_layout, null);
        }

        //Create a link between the xml layout and the java class
        TextView myItemText = (TextView) convertView.findViewById(R.id.nav_drawer_item_caption);
        ImageView myItemImg = (ImageView) convertView.findViewById(R.id.nav_drawer_item_img);
        //DrawerItem myDrawerItem = new DrawerItem(myItemText, myItemImg);
        DrawerItem myItem = super.getItem(position);
        myItemText.setText(myItem.getCaption());
        if (myItem.getImage() != null) {myItemImg.setImageResource(myItem.getImage());}

        return convertView;
    }
}
