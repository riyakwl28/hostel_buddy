package com.example.android.notification.models;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.notification.Expence;
import com.example.android.notification.Fragments.ProfileFragment;
import com.example.android.notification.Fragments.UsersFragment;
import com.example.android.notification.Income;
import com.example.android.notification.NotificationsFragment;


/**
 * Created by Riya Khandelwal on 10-04-2018.
 */

public class ExpensePagerAdapter extends FragmentPagerAdapter {
    public ExpensePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                Income incomeFragment=new Income();
                return incomeFragment;
            case 1:
                Expence expenceFragment=new Expence();
                return expenceFragment;
            default:
                return null;

        }
    }
    @Override
    public int getCount(){
        return 2;
    }
}

