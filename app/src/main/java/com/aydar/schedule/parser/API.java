package com.aydar.schedule.parser;

import java.util.List;

/**
 * Created by aydar on 13.02.2017.
 */

public class API {

    public List<Group> groups;
    public List<Teacher> teachers;
    public List<Shedule> shedule;

    public class Shedule {

        private int id;
        private int day;
        private String soname;
        private String name;
        private String patronymic;
        private String cabinet;
        private String group;
        private int lessonNumber;
        private String lesson;

        public int getId() {
            return id;
        }

        public int getDay() {
            return day;
        }

        public String getSoname() {
            return soname;
        }

        public String getName() {
            return name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public String getCabinet() {
            return cabinet;
        }

        public String getGroup() {
            return group;
        }

        public int getLessonNumber() {
            return lessonNumber;
        }

        public String getLesson() {
            return lesson;
        }
    }

    public class Group {

        private int id;
        private String group;
        private String specialty;
        private int course;
        private String curatorSoname;
        private String curatorName;
        private String curatorPatronymic;

        public int getId() {
            return id;
        }

        public String getGroup() {
            return group;
        }

        public String getSpecialty() {
            return specialty;
        }

        public int getCourse() {
            return course;
        }

        public String getCuratorSoname() {
            return curatorSoname;
        }

        public String getCuratorName() {
            return curatorName;
        }

        public String getCuratorPatronymic() {
            return curatorPatronymic;
        }
    }

    public class Teacher {

        private int id;
        private String soname;
        private String name;
        private String patronymic;
        private String cabinet;

        public int getId() {
            return id;
        }

        public String getSoname() {
            return soname;
        }

        public String getName() {
            return name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public String getCabinet() {
            return cabinet;
        }
    }
}
