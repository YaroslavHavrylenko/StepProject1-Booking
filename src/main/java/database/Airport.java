package database;

public enum Airport {
    BAKU("GYD"),
    GANJA("KVD"),
    BAHRAIN("BAH"),
    SOFIA("SOF"),
    BATUMI("BUS"),
    TBILISI("TBS"),
    ISTANBUL("IST"),
    ANKARA("ESB"),
    KYIV("KBP");

    private final String code;

    Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
