package com.aydar.schedule.teachers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aydar.schedule.R;
import com.aydar.schedule.parser.API.Teacher;
import com.aydar.schedule.teachers.Labs.TeacherLab;

import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class SelectTeacherFragment extends Fragment {
    private RecyclerView mSelectTeacherRecyclerView;
    private List<Teacher> mTeacher;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchTeacherListTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mSelectTeacherRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);
        mSelectTeacherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mSelectTeacherRecyclerView.setAdapter(new SelectTeacherAdapter(mTeacher));
        }
    }

    private class SelectTeacherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Teacher mTeacher;

        private TextView mTeacherNameTextView;

        private SelectTeacherHolder(View itemView) {
            super(itemView);
            mTeacherNameTextView = (TextView)
                    itemView.findViewById(R.id.list_item_teacher_title_text_view);
            mTeacherNameTextView.setOnClickListener(this);
        }

        private void bindTeacher(Teacher teacher) {
            mTeacher = teacher;
            mTeacherNameTextView.setText(mTeacher.getSoname() + " " +
                    mTeacher.getName() + " " +
                    mTeacher.getPatronymic());
        }

        @Override
        public void onClick(View v) {
            Log.i("action", mTeacher.getName());
            Intent intent = ShedulePagerActivity.newIntent(getActivity(), mTeacher.getId());
            startActivity(intent);
        }

    }

    private class SelectTeacherAdapter extends RecyclerView.Adapter<SelectTeacherHolder> {

        private List<Teacher> mTeacherList;

        private SelectTeacherAdapter(List<Teacher> teachers) {
            mTeacherList = teachers;
        }

        @Override
        public SelectTeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_teacher, parent, false);
            return new SelectTeacherHolder(view);
        }

        @Override
        public void onBindViewHolder(SelectTeacherHolder holder, int position) {
            Teacher teacher = mTeacherList.get(position);
            holder.bindTeacher(teacher);
        }

        @Override
        public int getItemCount() {
            return mTeacherList.size();
        }
    }

    private class FetchTeacherListTask extends AsyncTask<Void, Void, List<Teacher>> {

        @Override
        protected List<Teacher> doInBackground(Void... params) {
            TeacherLab teachersLab = TeacherLab.get();
            return teachersLab.getTeachersList();
        }

        @Override
        protected void onPostExecute(List<Teacher> teachers) {
            mTeacher = teachers;
            setupAdapter();
        }
    }
}
