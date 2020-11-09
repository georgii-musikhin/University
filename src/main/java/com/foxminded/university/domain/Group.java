package com.foxminded.university.domain;

import java.util.Objects;

public class Group {
    private int groupID;
    private String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return groupID == group.groupID &&
                Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, groupName);
    }

    @Override
    public String toString() {
        return groupName;
    }
}
