package com.aydar.schedule.groups;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aydar.schedule.SingleShedulePagerActivity;
import com.aydar.schedule.groups.Labs.SheduleForGroupLab;

/**
 * Created by aydar on 08.02.2017.
 */

public class ShedulePagerActivity extends SingleShedulePagerActivity {

    public static final String EXTRA_GROUP_ID =
            "com.aydar.shedule.group.group_id";


    public static Intent newIntent(Context packageContext, int groupId) {
        return newIntent(packageContext, ShedulePagerActivity.class, groupId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SetAdapterTask().execute();
    }

    private class SetAdapterTask extends AsyncTask<Void, Void, SheduleForGroupLab> {

        @Override
        protected SheduleForGroupLab doInBackground(Void... params) {
            int groupId = getIntent()
                    .getIntExtra(EXTRA_GROUP_ID, -1);
            return SheduleForGroupLab.get(groupId);
        }

        @Override
        protected void onPostExecute(final SheduleForGroupLab sheduleLabForGroup) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            getViewPager().setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                @Override
                public Fragment getItem(int day) { // получаю расписание на день
                    return SheduleFragment.newInstance(day);
                }

                @Override
                public int getCount() { // сколько дней всего
                    if (sheduleLabForGroup.getShedule() != null) {
                        return sheduleLabForGroup.getShedule().size();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }
}
