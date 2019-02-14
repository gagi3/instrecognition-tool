package model;

public enum Instrument {

    cel, cla, flu, gac, gel, org, pia, sax, tru, vio, voi;

    @Override
    public String toString() {
        switch (this) {
            case cel: return "cello";
            case cla: return "clarinet";
            case flu: return "flute";
            case gac: return "acoustic guitar";
            case gel: return "electric guitar";
            case org: return "organ";
            case pia: return "piano";
            case sax: return "saxophone";
            case tru: return "trumpet";
            case vio: return "violin";
            case voi: return "human singing voice";
        }
        return "invalid instrument";
    }

}
