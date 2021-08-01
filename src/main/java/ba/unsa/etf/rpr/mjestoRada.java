package ba.unsa.etf.rpr;

public enum mjestoRada {
    REMOTE,ONSITE;

    @Override
    public String toString() {
        switch(this) {
            case REMOTE: return "Remote";
            case ONSITE: return "Onsite";
            default: throw new IllegalArgumentException();
        }
    }
}
