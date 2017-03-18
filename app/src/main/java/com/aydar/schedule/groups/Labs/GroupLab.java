package com.aydar.schedule.groups.Labs;

import com.aydar.schedule.parser.API;
import com.aydar.schedule.groups.Fetchers.GroupsFetcher;

import java.util.List;

/**
 * Created by aydar on 13.02.2017.
 */

public class GroupLab {
    private static GroupLab sGroupLab;

    private List<API.Group> mGroups;

    public static GroupLab get() {
        if (sGroupLab == null) {
            sGroupLab = new GroupLab();
        }
        return sGroupLab;
    }
    private GroupLab() {
        mGroups = new GroupsFetcher().getGroupsList();
    }

    public List<API.Group> getGroupsList() {
        return mGroups;
    }
}
