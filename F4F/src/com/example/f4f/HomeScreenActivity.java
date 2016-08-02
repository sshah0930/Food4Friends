package com.example.f4f;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class HomeScreenActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private HomeScreenActivity mContext;
    private ResideMenuItem itemBROWSE;
    private ResideMenuItem itemCREATE;
    private ResideMenuItem itemCONTACTS;
    private ResideMenuItem itemSettings;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        changeFragment(new HomeFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.5f);

        // create menu items;
        itemBROWSE    = new ResideMenuItem(this, R.drawable.icon_home,     "Browse Recipes");
        itemCREATE  = new ResideMenuItem(this, R.drawable.icon_settings,  "Create Recipes");
        itemCONTACTS = new ResideMenuItem(this, R.drawable.icon_profile, "Friends");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");

        itemBROWSE.setOnClickListener(this);
        itemCREATE.setOnClickListener(this);
        itemCONTACTS.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemBROWSE, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCREATE, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCONTACTS, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        //resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemBROWSE){
            //changeFragment(new HomeFragment());
        	changeFragment(new CuisinesFragment());
        }
        else if(view == itemCREATE){
        	//changeFragment(new CreateRecipeFragment());
        	
        }
        else if(view == itemCONTACTS){
        	changeFragment(new ContactsFragment());
        	
        }
        
        else if(view == itemSettings){
        	//changeFragment(new SettingsFragment());
        	
        	
        }

        resideMenu.closeMenu();
        
        
    }

    public void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
