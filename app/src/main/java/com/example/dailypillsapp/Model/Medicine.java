package com.example.dailypillsapp.Model;

import java.io.Serializable;

public class Medicine implements Serializable {

    String id, medicationName, medicationRepetitionType, medicationRepetition, medicationAmount, medicationType, timeAtMillis, imageUri, time;
    String stateSaturday = "", stateSunday = "", stateMonday = "", stateTuesday = "", stateWednesday = "", stateThursday = "", stateFriday = "";

    int dosesNumber = 0, dosesNumberTaken = 0;

    String stateDose1 = "", stateDose2 = "", stateDose3 = "", stateDose4 = "", stateDose5 = "", stateDose6 = "", stateDose7 = "", stateDose8 = "", stateDose9 = "", stateDose10 = "", stateDose11 = "", stateDose12 = "";
    String stateDose13 = "", stateDose14 = "", stateDose15 = "", stateDose16 = "", stateDose17 = "", stateDose18 = "", stateDose19 = "", stateDose20 = "", stateDose21 = "", stateDose22 = "", stateDose23 = "", stateDose24 = "";


    public Medicine() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationRepetitionType() {
        return medicationRepetitionType;
    }

    public void setMedicationRepetitionType(String medicationRepetitionType) {
        this.medicationRepetitionType = medicationRepetitionType;
    }

    public String getMedicationRepetition() {
        return medicationRepetition;
    }

    public void setMedicationRepetition(String medicationRepetition) {
        this.medicationRepetition = medicationRepetition;
    }

    public String getMedicationAmount() {
        return medicationAmount;
    }

    public void setMedicationAmount(String medicationAmount) {
        this.medicationAmount = medicationAmount;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public String getTimeAtMillis() {
        return timeAtMillis;
    }

    public void setTimeAtMillis(String timeAtMillis) {
        this.timeAtMillis = timeAtMillis;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStateSaturday() {
        return stateSaturday;
    }

    public void setStateSaturday(String stateSaturday) {
        this.stateSaturday = stateSaturday;
    }

    public String getStateSunday() {
        return stateSunday;
    }

    public void setStateSunday(String stateSunday) {
        this.stateSunday = stateSunday;
    }

    public String getStateMonday() {
        return stateMonday;
    }

    public void setStateMonday(String stateMonday) {
        this.stateMonday = stateMonday;
    }

    public String getStateTuesday() {
        return stateTuesday;
    }

    public void setStateTuesday(String stateTuesday) {
        this.stateTuesday = stateTuesday;
    }

    public String getStateWednesday() {
        return stateWednesday;
    }

    public void setStateWednesday(String stateWednesday) {
        this.stateWednesday = stateWednesday;
    }

    public String getStateThursday() {
        return stateThursday;
    }

    public void setStateThursday(String stateThursday) {
        this.stateThursday = stateThursday;
    }

    public String getStateFriday() {
        return stateFriday;
    }

    public void setStateFriday(String stateFriday) {
        this.stateFriday = stateFriday;
    }

    public int getDosesNumber() {
        return dosesNumber;
    }

    public void setDosesNumber(int dosesNumber) {
        this.dosesNumber = dosesNumber;
    }

    public int getDosesNumberTaken() {
        return dosesNumberTaken;
    }

    public void setDosesNumberTaken(int dosesNumberTaken) {
        this.dosesNumberTaken = dosesNumberTaken;
    }

    public String getStateDose1() {
        return stateDose1;
    }

    public void setStateDose1(String stateDose1) {
        this.stateDose1 = stateDose1;
    }

    public String getStateDose2() {
        return stateDose2;
    }

    public void setStateDose2(String stateDose2) {
        this.stateDose2 = stateDose2;
    }

    public String getStateDose3() {
        return stateDose3;
    }

    public void setStateDose3(String stateDose3) {
        this.stateDose3 = stateDose3;
    }

    public String getStateDose4() {
        return stateDose4;
    }

    public void setStateDose4(String stateDose4) {
        this.stateDose4 = stateDose4;
    }

    public String getStateDose5() {
        return stateDose5;
    }

    public void setStateDose5(String stateDose5) {
        this.stateDose5 = stateDose5;
    }

    public String getStateDose6() {
        return stateDose6;
    }

    public void setStateDose6(String stateDose6) {
        this.stateDose6 = stateDose6;
    }

    public String getStateDose7() {
        return stateDose7;
    }

    public void setStateDose7(String stateDose7) {
        this.stateDose7 = stateDose7;
    }

    public String getStateDose8() {
        return stateDose8;
    }

    public void setStateDose8(String stateDose8) {
        this.stateDose8 = stateDose8;
    }

    public String getStateDose9() {
        return stateDose9;
    }

    public void setStateDose9(String stateDose9) {
        this.stateDose9 = stateDose9;
    }

    public String getStateDose10() {
        return stateDose10;
    }

    public void setStateDose10(String stateDose10) {
        this.stateDose10 = stateDose10;
    }

    public String getStateDose11() {
        return stateDose11;
    }

    public void setStateDose11(String stateDose11) {
        this.stateDose11 = stateDose11;
    }

    public String getStateDose12() {
        return stateDose12;
    }

    public void setStateDose12(String stateDose12) {
        this.stateDose12 = stateDose12;
    }

    public String getStateDose13() {
        return stateDose13;
    }

    public void setStateDose13(String stateDose13) {
        this.stateDose13 = stateDose13;
    }

    public String getStateDose14() {
        return stateDose14;
    }

    public void setStateDose14(String stateDose14) {
        this.stateDose14 = stateDose14;
    }

    public String getStateDose15() {
        return stateDose15;
    }

    public void setStateDose15(String stateDose15) {
        this.stateDose15 = stateDose15;
    }

    public String getStateDose16() {
        return stateDose16;
    }

    public void setStateDose16(String stateDose16) {
        this.stateDose16 = stateDose16;
    }

    public String getStateDose17() {
        return stateDose17;
    }

    public void setStateDose17(String stateDose17) {
        this.stateDose17 = stateDose17;
    }

    public String getStateDose18() {
        return stateDose18;
    }

    public void setStateDose18(String stateDose18) {
        this.stateDose18 = stateDose18;
    }

    public String getStateDose19() {
        return stateDose19;
    }

    public void setStateDose19(String stateDose19) {
        this.stateDose19 = stateDose19;
    }

    public String getStateDose20() {
        return stateDose20;
    }

    public void setStateDose20(String stateDose20) {
        this.stateDose20 = stateDose20;
    }

    public String getStateDose21() {
        return stateDose21;
    }

    public void setStateDose21(String stateDose21) {
        this.stateDose21 = stateDose21;
    }

    public String getStateDose22() {
        return stateDose22;
    }

    public void setStateDose22(String stateDose22) {
        this.stateDose22 = stateDose22;
    }

    public String getStateDose23() {
        return stateDose23;
    }

    public void setStateDose23(String stateDose23) {
        this.stateDose23 = stateDose23;
    }

    public String getStateDose24() {
        return stateDose24;
    }

    public void setStateDose24(String stateDose24) {
        this.stateDose24 = stateDose24;
    }
}
