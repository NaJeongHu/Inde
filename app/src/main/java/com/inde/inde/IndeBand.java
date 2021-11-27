package com.inde.inde;

public class IndeBand {

    private Integer groupId;
    private String groupName;
    private String groupImageUrl;

    public IndeBand() { }

    public IndeBand(Integer groupId, String groupName, String groupImageUrl) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImageUrl = groupImageUrl;
    }

    public Integer getGroupId() { return groupId; }

    public void setGroupId(Integer groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }

    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getGroupImageUrl() { return groupImageUrl; }

    public void setGroupImageUrl(String groupImageUrl) { this.groupImageUrl = groupImageUrl; }
}
