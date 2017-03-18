package com.aydar.schedule.teachers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aydar.schedule.SingleShedulePagerActivity;
import com.aydar.schedule.teachers.Labs.SheduleForTeacherLab;

/**
 * Created by aydar on 28.02.2017.
 */

public class ShedulePagerActivity extends SingleShedulePagerActivity {

    public static final String EXTRA_TEACHER_ID =
            "com.aydar.shedule.teacher.teacher_id";


    public static Intent newIntent(Context packageContext, int teacherId) {
        return newIntent(packageContext, ShedulePagerActivity.class, teacherId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SetAdapterTask().execute();
    }

    private class SetAdapterTask extends AsyncTask<Void, Void, SheduleForTeacherLab> {

        @Override
        protected SheduleForTeacherLab doInBackground(Void... params) {
            int teacherId = getIntent()
                    .getIntExtra(EXTRA_TEACHER_ID, -1);
            return SheduleForTeacherLab.get(teacherId);
        }

        @Override
        protected void onPostExecute(final SheduleForTeacherLab sheduleForTeacherLab) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            getViewPager().setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                @Override
                public Fragment getItem(int day) { // получаю расписание на день
                    return SheduleFragment.newInstance(day);
                }

                @Override
                public int getCount() { // сколько дней всего
                    if (sheduleForTeacherLab.getShedule() != null) {
                        return sheduleForTeacherLab.getShedule().size();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

}
