package com.aydar.schedule.groups;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aydar.schedule.R;
import com.aydar.schedule.groups.Labs.SheduleForGroupLab;
import com.aydar.schedule.parser.API.Shedule;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 01.02.2017.
 */

public class SheduleFragment extends Fragment {

    private static final String ARG_SHEDULE_DAY_GROUP = "shedule_day_group";

    private RecyclerView mSheduleRecyclerView;

    private List<Shedule> mSheduleForDay = new ArrayList<>();

    public static SheduleFragment newInstance(int day) {
        Bundle args = new Bundle();
        args.putInt(ARG_SHEDULE_DAY_GROUP, day);

        SheduleFragment fragment = new SheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mSheduleRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_recycler_view);
        mSheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setupAdapter();

        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mSheduleRecyclerView.setAdapter(new SheduleAdapter(mSheduleForDay));
        }
    }

    private class SheduleHolder extends RecyclerView.ViewHolder {
        private TextView mLesson;

        public SheduleHolder(View itemView) {
            super(itemView);
            mLesson = (TextView) itemView.findViewById(R.id.lesson);
        }

        public void bindSheduleItem(Shedule item) {
            mLesson.setText(item.getLessonNumber() + " " + item.getLesson());
        }

        public void bindSheduleNotFound() {
            mLesson.setText("Рассписание не найдено");
        }
    }

    private class SheduleAdapter extends RecyclerView.Adapter<SheduleHolder> {
        private List<Shedule> mSheduleItems;
        private boolean isSheduleItemsNull = false;
        public SheduleAdapter(List<Shedule> sheduleItems) {
            mSheduleItems = sheduleItems;
        }

        @Override
        public SheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.shedule_item_fragment, parent, false);

            return new SheduleHolder(view);
        }

        @Override
        public void onBindViewHolder(SheduleHolder sheduleHolder, int position) {
            if (!isSheduleItemsNull) {
                Shedule sheduleForGroup = mSheduleItems.get(position);
                sheduleHolder.bindSheduleItem(sheduleForGroup);
            } else {
                sheduleHolder.bindSheduleNotFound();
            }
        }

        @Override
        public int getItemCount() {
            if (mSheduleItems != null) {
                return mSheduleItems.size();
            } else {
                isSheduleItemsNull = true;
                return 1;
            }
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<Shedule>> {

        @Override
        protected List<Shedule> doInBackground(Void... params) {
            int groupId = getActivity().getIntent()
                    .getIntExtra(ShedulePagerActivity.EXTRA_GROUP_ID, -1);
            int day = getArguments().getInt(ARG_SHEDULE_DAY_GROUP);
            SheduleForGroupLab sheduleLabForGroup = SheduleForGroupLab.get(groupId);
            return sheduleLabForGroup.getSheduleForDay(day);
        }

        @Override
        protected void onPostExecute(List<Shedule> shedule) {
            mSheduleForDay = shedule;
            setupAdapter();
        }
    }
}
