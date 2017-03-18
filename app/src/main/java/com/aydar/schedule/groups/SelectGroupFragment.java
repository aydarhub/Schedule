package com.aydar.schedule.groups;

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
import com.aydar.schedule.groups.Labs.GroupLab;
import com.aydar.schedule.parser.API.Group;

import java.util.List;

/**
 * Created by aydar on 12.02.2017.
 */

public class SelectGroupFragment extends Fragment {

    private RecyclerView mSelectGroupRecyclerView;
    private List<Group> mGroups;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchGroupListTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mSelectGroupRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);
        mSelectGroupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mSelectGroupRecyclerView.setAdapter(new SelectGroupAdapter(mGroups));
        }
    }

    private class SelectGroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Group mGroup;

        public TextView mGroupNameTextView;

        public SelectGroupHolder(View itemView) {
            super(itemView);
            mGroupNameTextView = (TextView)
                    itemView.findViewById(R.id.list_item_group_title_text_view);
            mGroupNameTextView.setOnClickListener(this);
        }

        public void bindGroup(Group group) {
            mGroup = group;
            mGroupNameTextView.setText(mGroup.getGroup());
        }

        @Override
        public void onClick(View v) {
            Log.i("action", mGroup.getGroup());
            Intent intent = ShedulePagerActivity.newIntent(getActivity(), mGroup.getId());
            startActivity(intent);
        }

    }

    private class SelectGroupAdapter extends RecyclerView.Adapter<SelectGroupHolder> {

        private List<Group> mGroupList;

        public SelectGroupAdapter(List<Group> groups) {
            mGroupList = groups;
        }

        @Override
        public SelectGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_group, parent, false);
            return new SelectGroupHolder(view);
        }

        @Override
        public void onBindViewHolder(SelectGroupHolder holder, int position) {
            Group group = mGroupList.get(position);
            holder.bindGroup(group);
        }

        @Override
        public int getItemCount() {
            return mGroupList.size();
        }
    }

    private class FetchGroupListTask extends AsyncTask<Void, Void, List<Group>> {

        @Override
        protected List<Group> doInBackground(Void... params) {
            GroupLab groupLab = GroupLab.get();
            return groupLab.getGroupsList();
        }

        @Override
        protected void onPostExecute(List<Group> groups) {
            mGroups = groups;
            setupAdapter();
        }
    }
}
