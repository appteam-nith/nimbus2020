package com.nith.appteam.nimbus2020.Models;

public class Schedule {
    String name;
    String department;
    String time;
    String venue;
    String info;
    public Schedule(){

    }
    public Schedule(String name, String department, String time, String venue, String info) {
        this.name = name;
        this.department = department;
        this.time = time;
        this.venue = venue;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
