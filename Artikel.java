public class Artikel {

    private int anzahl;
    private String name;

    public Artikel(int anzahl, String name) {
        this.anzahl = anzahl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return anzahl + "x " + name;
    }
}
