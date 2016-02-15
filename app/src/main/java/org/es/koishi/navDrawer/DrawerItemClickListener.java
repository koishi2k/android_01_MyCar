package org.es.koishi.navDrawer;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.es.koishi.mycar.R;
import org.es.koishi.mycar.fragments.Fr_01_LocationAcquisition;
import org.es.koishi.mycar.fragments.Fr_02_LocationNavigation;
import org.es.koishi.mycar.myMapTest;
import org.es.koishi.mycar.myTestFragment;

import java.util.List;

/**
 * Created by enrique.perez on 05/02/2016.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private static final String TAG = DrawerItemClickListener.class.getSimpleName();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AppCompatActivity myInvokerActivity;
        myInvokerActivity = this.getActivity(view);

        DrawerItem myItem = (DrawerItem) parent.getAdapter().getItem(position);
        String myText = myItem.getCaption();
        //String myText = parent.getAdapter().getItem(position).toString();
        Log.i(TAG, "He pulsado el item <" + position + "> del navigation drawer");
        Log.i(TAG, "El texto de este item es: " + myText);

        Fragment myFragment;
        switch(position) {
            case 0:
                //myFragment = myMapTest.newInstance("","");
                myFragment = new myMapTest();
                break;
            case 1:
                myFragment = new Fr_02_LocationNavigation();
                break;
            case 2:
                myFragment = new Fr_01_LocationAcquisition();
                break;
            default:
                myFragment = myTestFragment.newInstance(myText, "");
        }

        //FragmentManager myFragmentMngr = myInvoker.getSupportFragmentManager();
        //FragmentManager myFragmentMngr = myInvoker.getFragmentManager();
        FragmentManager myFragmentMngr = myInvokerActivity.getSupportFragmentManager();
        FragmentTransaction myFragmentChange = myFragmentMngr.beginTransaction();
        myFragmentChange.replace(R.id.content_frame, myFragment);
        myFragmentChange.commit();

        //Highlight selection on drawer
        ListView myNavDrawer = (ListView) myInvokerActivity.findViewById(R.id.drawer_list);
        myNavDrawer.setItemChecked(position, true);

        //Close drawer
        DrawerLayout myDrawer = (DrawerLayout) myInvokerActivity.findViewById(R.id.drawer_main_layout);
        myDrawer.closeDrawer(Gravity.LEFT);
    }

    private AppCompatActivity getActivity(View invoker) {
        AppCompatActivity result;
        result = null;
        if (invoker.getContext() instanceof AppCompatActivity) {
            result = (AppCompatActivity) invoker.getContext();
        }
        return result;
    }
}
