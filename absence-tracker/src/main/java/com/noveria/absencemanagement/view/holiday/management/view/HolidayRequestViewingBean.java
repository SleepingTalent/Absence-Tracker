package com.noveria.absencemanagement.view.holiday.management.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lynseymcgregor on 04/07/2015.
 */
public class HolidayRequestViewingBean {

    Date start;
    Date end;
    String status;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Long id;
    private String firstName;
    private String lastName;

    public Date getStart() {
        return start;
    }

    public String getStartDateStr() {
        return (start != null) ? dateFormat.format(start) : "";
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndDateStr() {
        return (end != null) ? dateFormat.format(end) : "";
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return getFirstName()+" "+getLastName();
    }
}
