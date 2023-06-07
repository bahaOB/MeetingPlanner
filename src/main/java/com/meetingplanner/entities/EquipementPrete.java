package com.meetingplanner.entities;

public class EquipementPrete{
    private String lib;

    private int nbreEquipementsPretes;

    public EquipementPrete() {
    }

    public EquipementPrete(String lib, int nbreEquipementsPretes) {
        this.lib = lib;
        this.nbreEquipementsPretes = nbreEquipementsPretes;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public int getNbreEquipementsPretes() {
        return nbreEquipementsPretes;
    }

    public void setNbreEquipementsPretes(int nbreEquipementsPretes) {
        this.nbreEquipementsPretes = nbreEquipementsPretes;
    }
}
