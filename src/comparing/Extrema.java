package comparing;

public enum Extrema {
    Minimum, Maximum;

    public static Extrema valueOf(int selectedIndex) {
        return Extrema.values()[selectedIndex];
    }
}
