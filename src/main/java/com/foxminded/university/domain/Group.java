package com.foxminded.university.domain;

import java.util.List;

public class Group {
    private int groupID;
    private String groupName;
    private List<Student> students;

    public Group(String groupName) {
        this.groupName = groupName;
    }
}
