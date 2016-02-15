package org.es.koishi.mycar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/*
import org.es.koishi.navDrawer.DrawerItem;
import org.es.koishi.navDrawer.DrawerListAdapter;
*/
import org.es.koishi.navDrawer.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate drawer
        //1 - Create arraylist with options
        String[] myDrawerOptionsStr = this.getResources().getStringArray(R.array.drawer_main_options);
        Log.i(TAG, "Drawer options found (" + myDrawerOptionsStr.length + ")");
        ArrayList<DrawerItem> myDrawerOptions = new ArrayList<DrawerItem>(myDrawerOptionsStr.length);
        for (String myOptionStr : myDrawerOptionsStr) {
            DrawerItem myItemTmp = new DrawerItem(myOptionStr);
            myDrawerOptions.add(myItemTmp);
            Log.i(TAG, "Drawer option added (" + myOptionStr + ")");
        }
        //2 - create the adapter based on the arraylist of rows
        DrawerListAdapter myAdapter = new DrawerListAdapter(this,myDrawerOptions);
        Log.i(TAG, "Adapter created (" + myAdapter.toString() + ")");
        //3 - connect the adapter to the drawer listview
        ListView myDrawerList = (ListView) this.findViewById(R.id.drawer_list);
        myDrawerList.setAdapter(myAdapter);
        Log.i(TAG, "Adapter attached (" + myDrawerList.getAdapter().toString() + ")");

        //4 - Bind listener to drawer item
        //View.OnClickListener navItemListener = (View.OnClickListener) new DrawerItemClickListener();
        myDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //5 - load default fragment on create
        //http://stackoverflow.com/questions/24267155/navigationdrawer-is-it-possible-to-simulate-perform-click-progammatically
        int myDefaultPosition = 0;
        myDrawerList.performItemClick(
                        myAdapter.getView(myDefaultPosition, null, myDrawerList),
                        myDefaultPosition,
                        myAdapter.getItemId(myDefaultPosition)
                );
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }*/
}
