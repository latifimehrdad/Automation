package ir.ngra.automation.models;

import android.graphics.drawable.Drawable;

public class MD_HomeActionMenu {

    private String title;

    private Drawable icon;

    private int action;

    public MD_HomeActionMenu(String title, Drawable icon, int action) {
        this.title = title;
        this.icon = icon;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }


}
