package model;

public enum Drums {
    dru, nod;

    @Override
    public String toString() {
        switch (this) {
            case dru: return "drums";
            case nod: return "no drums";
        }
        return "invalid drums";
    }
}
