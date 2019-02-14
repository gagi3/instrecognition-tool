package model;

public enum Genre {

    cou_fol, cla, pop_roc, lat_sou, jaz_blu;

    @Override
    public String toString() {
        switch (this) {
            case cla: return "classical";
            case cou_fol: return "country-folk";
            case pop_roc: return "pop-rock";
            case jaz_blu: return "jazz-blues";
            case lat_sou: return "latin-soul";
        }
        return "invalid genre";
    }

}
