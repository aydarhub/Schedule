package com.aydar.schedule.groups.Labs;

import com.aydar.schedule.Labs.SheduleLab;
import com.aydar.schedule.parser.API.Shedule;
import com.aydar.schedule.groups.Fetchers.SheduleForGroupFetcher;

import java.util.List;

/**
 * Created by aydar on 08.02.2017.
 */

public class SheduleForGroupLab extends SheduleLab{
    private static SheduleForGroupLab sSheduleLab;

    private static int mGroupId;

    public static SheduleForGroupLab get(int groupId) {
        if (sSheduleLab == null || mGroupId != groupId) {
            sSheduleLab = new SheduleForGroupLab(groupId);
            mGroupId = groupId;
        }
        return sSheduleLab;
    }
    private SheduleForGroupLab(int groupId) {
        setShedule(new SheduleForGroupFetcher().getSheduleForDays(groupId));

    }

    public List<Shedule> getSheduleForDay(int dayNumber) {
        return super.getSheduleForDay(dayNumber);
    }

}
