package com.aydar.schedule.teachers.Labs;

import com.aydar.schedule.parser.API.Teacher;

import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class TeacherLab {
    private static TeacherLab sTeacherLab;

    private List<Teacher> mTeachers;

    public static TeacherLab get() {
        if (sTeacherLab == null) {
            sTeacherLab = new TeacherLab();
        }
        return sTeacherLab;
    }
    private TeacherLab() {
        mTeachers = new TeachersFetcher().getTeachersList();
    }

    public List<Teacher> getTeachersList() {
        return mTeachers;
    }
}
