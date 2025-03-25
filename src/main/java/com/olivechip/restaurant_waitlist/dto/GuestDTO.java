package com.olivechip.restaurant_waitlist.dto;

public class GuestDTO {
    private Integer id;
    private String name;
    private Integer partySize;
    private String phoneNumber;

    public GuestDTO() {
    }

    public GuestDTO(Integer id, String name, Integer partySize, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.partySize = partySize;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}