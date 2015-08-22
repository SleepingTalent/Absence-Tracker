package com.noveria.absencemanagement.model.absence.entity;

/**
 * Created by lynseymcgregor on 15/08/2015.
 */
public enum AbsenceReason {

    MATERNITY("Maternity",true),
    PATERNITY("Paternity",true),
    CARERS("Carer's",true),
    BEREAVEMENT("Bereavement",true),
    COLD_FLU("Cold/Flu",false),
    STRESS_DEPRESSION("Stress/Depression",false),
    HEADACHE_MIGRAINE("Headache",false),
    BACK_PROBLEMS("Back Problems",false),
    GI_PROBLEMS("GastroIntestinal",false),
    DENTAL_PROBLEMS("Dental",false);

    private String displayName;
    private boolean isAuthorised;

    AbsenceReason(String displayName, boolean isAuthorised) {
        this.displayName = displayName;
        this.isAuthorised = isAuthorised;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public static AbsenceReason findByName(String name) {
        for(AbsenceReason type : AbsenceReason.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" AbsenceReason not supported!");
    }

    public static AbsenceReason findByDiplayName(String name) {
        for(AbsenceReason type : AbsenceReason.values()) {
            if(type.getDisplayName().equals(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" AbsenceReason not supported!");
    }
}
