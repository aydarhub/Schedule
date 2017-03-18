package com.aydar.schedule.teachers;

import android.support.v4.app.Fragment;

import com.aydar.schedule.SingleFragmentActivity;

/**
 * Created by aydar on 20.02.2017.
 */

public class SelectTeacherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectTeacherFragment();
    }
}
