package com.aydar.schedule.teachers;

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
import com.aydar.schedule.parser.API.Shedule;
import com.aydar.schedule.teachers.Labs.SheduleForTeacherLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 28.02.2017.
 */

public class SheduleFragment extends Fragment {

    private static final String ARG_SHEDULE_DAY_TEACHER = "shedule_day_teacher";

    private RecyclerView mSheduleRecyclerView;

    private List<Shedule> mSheduleForDay = new ArrayList<>();

    public static SheduleFragment newInstance(int day) {
        Bundle args = new Bundle();
        args.putInt(ARG_SHEDULE_DAY_TEACHER, day);

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
        private TextView mGroup;

        public SheduleHolder(View itemView) {
            super(itemView);
            mGroup = (TextView) itemView.findViewById(R.id.group);
        }

        public void bindSheduleItem(Shedule item) {
            mGroup.setText(item.getLessonNumber() + " " + item.getGroup());
        }

        public void bindSheduleNotFound() {
            mGroup.setText("Рассписание не найдено");
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
                Shedule sheduleForTeacher = mSheduleItems.get(position);
                sheduleHolder.bindSheduleItem(sheduleForTeacher);
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
            int teacherId = getActivity().getIntent()
                    .getIntExtra(ShedulePagerActivity.EXTRA_GROUP_ID, -1);
            int day = getArguments().getInt(ARG_SHEDULE_DAY_TEACHER);
            SheduleForTeacherLab sheduleForTeacherLab = SheduleForTeacherLab.get(teacherId);
            return sheduleForTeacherLab.getSheduleForDay(day);
        }

        @Override
        protected void onPostExecute(List<Shedule> shedule) {
            mSheduleForDay = shedule;
            setupAdapter();
        }
    }
}
