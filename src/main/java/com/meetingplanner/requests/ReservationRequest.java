package com.meetingplanner.requests;

import java.io.Serializable;

public class ReservationRequest implements Serializable {

    private String reunion;
    private String creneau;
    private int nombrePersonnes;
    private String type;

    public ReservationRequest() {
    }

    public ReservationRequest(String reunion, String creneau, int nombrePersonnes, String type) {
        this.reunion = reunion;
        this.creneau = creneau;
        this.nombrePersonnes = nombrePersonnes;
        this.type = type;
    }

    public String getReunion() {
        return reunion;
    }

    public void setReunion(String reunion) {
        this.reunion = reunion;
    }

    public String getCreneau() {
        return creneau;
    }

    public void setCreneau(String creneau) {
        this.creneau = creneau;
    }

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
