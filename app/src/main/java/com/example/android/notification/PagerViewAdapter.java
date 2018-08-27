package com.example.android.notification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.notification.Fragments.ProfileFragment;
import com.example.android.notification.Fragments.UsersFragment;

/**
 * Created by Riya Khandelwal on 10-04-2018.
 */

class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
            ProfileFragment profileFragment=new ProfileFragment();
            return profileFragment;
            case 1:
                UsersFragment usersFragment=new UsersFragment();
                return usersFragment;
            case 2:
                NotificationsFragment notificationsFragment=new NotificationsFragment();
                return  notificationsFragment;
                default:
                    return null;
        }
    }
    @Override
    public int getCount(){
        return 3;
    }
}
