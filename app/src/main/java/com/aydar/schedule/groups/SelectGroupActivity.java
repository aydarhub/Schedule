package com.aydar.schedule.groups;



import android.support.v4.app.Fragment;

import com.aydar.schedule.SingleFragmentActivity;

/**
 * Created by aydar on 12.02.2017.
 */

public class SelectGroupActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectGroupFragment();
    }
}
