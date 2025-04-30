package com.example.campus_connect.DTOs.MembershipRequests;

public class MembershipRequestDTO {
    private Integer userId;
    private Integer clubId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
}
