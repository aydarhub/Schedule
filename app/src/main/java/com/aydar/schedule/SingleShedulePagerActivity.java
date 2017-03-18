package com.aydar.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by aydar on 27.02.2017.
 */

public class SingleShedulePagerActivity extends FragmentActivity {

    public static final String EXTRA_GROUP_ID =
            "com.aydar.shedule.group.group_id";

    private ViewPager mViewPager;


    protected static Intent newIntent(Context packageContext, Class sheduleActivity, int groupId) {
        Intent intent = new Intent(packageContext, sheduleActivity); // !!! Проверить
        intent.putExtra(EXTRA_GROUP_ID, groupId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_shedule_pager_view_pager);
    }

    protected ViewPager getViewPager() {
        return mViewPager;
    }
}
