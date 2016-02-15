package org.es.koishi.navDrawer;

import android.graphics.drawable.DrawableWrapper;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by enrique.perez on 13/01/2016.
 *
 * Auxiliary class intended to help in the population of the drawer list view.
 * It stores:
 *      · the caption
 *      · the icon drawable id
 * for each row that will appear in the list
 * Related to layout: drawer_item_layout.xml
 */
public class DrawerItem {

    private String myCaption;
    private Integer myImageRef;

    public DrawerItem(String newCaption, Integer newImageRef) {
        this.myCaption = newCaption;
        this.myImageRef = newImageRef;
    }

    public DrawerItem(String newCaption) {
        this(newCaption, null);
    }

    public String getCaption() {
        return myCaption;
    }

    public void setCaption(String newCaption) {
        this.myCaption = newCaption;
    }

    public Integer getImage() {
        return myImageRef;
    }

    public void setImage(int newImageRef) {
        this.myImageRef = Integer.valueOf(newImageRef);
    }
}
