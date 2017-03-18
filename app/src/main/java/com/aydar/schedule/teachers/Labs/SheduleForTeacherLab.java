package com.aydar.schedule.teachers.Labs;

import com.aydar.schedule.Labs.SheduleLab;
import com.aydar.schedule.parser.API;
import com.aydar.schedule.teachers.Fetchers.SheduleForTeacherFetcher;

import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class SheduleForTeacherLab extends SheduleLab {
    private static SheduleForTeacherLab sSheduleLab;

    private static int mTeacherId;

    public static SheduleForTeacherLab get(int teacherId) {
        if (sSheduleLab == null || mTeacherId != teacherId) {
            sSheduleLab = new SheduleForTeacherLab(teacherId);
            mTeacherId = teacherId;
        }
        return sSheduleLab;
    }
    private SheduleForTeacherLab(int teacherId) {
        setShedule(new SheduleForTeacherFetcher().getSheduleForDays(teacherId));

    }

    public List<API.Shedule> getSheduleForDay(int dayNumber) {
        return super.getSheduleForDay(dayNumber);
    }

}
