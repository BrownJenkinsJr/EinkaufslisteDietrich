import javax.swing.*;

public class Einkaufsliste extends AbstractListModel<String> {

    private List<Artikel> list = new List<>();

    public void add(Artikel artikel) {
        list.toFirst();
        while (list.hasAccess()) {
            if (list.getContent().getName().equals(artikel.getName())) {
                list.getContent().setAnzahl(list.getContent().getAnzahl() + artikel.getAnzahl());
                return;
            }
            list.next();
        }
        list.append(artikel);
    }

    public void remove(int index) {
        list.toFirst();
        for (int i = 0; i < index; i++) {
            list.next();
        }
        if (!list.hasAccess())
            return;

        Artikel content = list.getContent();
        if (content.getAnzahl() == 1) {
            list.remove();
        } else {
            content.setAnzahl(content.getAnzahl() - 1);
        }
    }

    public void listeAusgeben() {
        list.toFirst();
        while (list.hasAccess()) {
            System.out.println(list.getContent());
            list.next();
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        list.toFirst();
        while (list.hasAccess()) {
            size++;
            list.next();
        }
        return size;
    }

    @Override
    public String getElementAt(int index) {
        int i = 0;
        list.toFirst();
        while (list.hasAccess()) {
            if (i == index)
                return list.getContent().toString();
            i++;
            list.next();
        }
        return null;
    }

    public List<Artikel> getList() {
        return list;
    }
}
