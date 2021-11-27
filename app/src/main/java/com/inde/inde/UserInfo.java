package com.inde.inde;

public class UserInfo {

    private Integer userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String profileImgPath;
    private String profileImgUrl;
    private String type;
    private String status;
    private String groupName;
    private String groupImgUrl;

    private static final UserInfo ourInstance = new UserInfo();

    public static UserInfo getInstance() {
        return ourInstance;
    }

    private UserInfo() {}

}
