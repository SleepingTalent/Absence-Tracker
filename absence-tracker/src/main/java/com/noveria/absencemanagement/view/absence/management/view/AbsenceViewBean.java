package com.noveria.absencemanagement.view.absence.management.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
public class AbsenceViewBean {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private String reason;
    private String status;
    private String type;
    private Date start;
    private Date end;
    private Long id;
    private String fullName;
    private String employeeId;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return start;
    }

    public String getStartStr() {
        return simpleDateFormat.format(start);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndStr() {
        return simpleDateFormat.format(end);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
