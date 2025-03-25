package com.olivechip.restaurant_waitlist.dto;

import java.util.List;

public class GuestDTO {
    private Integer id;
    private String name;
    private Integer partySize;
    private String phoneNumber;
    private List<WaitlistEntryCombinedDTO> waitlistEntries;

    public GuestDTO() {
    }

    public GuestDTO(Integer id, String name, Integer partySize, String phoneNumber,
            List<WaitlistEntryCombinedDTO> waitlistEntries) {
        this.id = id;
        this.name = name;
        this.partySize = partySize;
        this.phoneNumber = phoneNumber;
        this.waitlistEntries = waitlistEntries;
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

    public List<WaitlistEntryCombinedDTO> getWaitlistEntries() {
        return waitlistEntries;
    }

    public void setWaitlistEntries(List<WaitlistEntryCombinedDTO> waitlistEntries) {
        this.waitlistEntries = waitlistEntries;
    }
}
