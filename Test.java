import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        //new GUI().setVisible(true);

        Einkaufsliste liste = new Einkaufsliste();

        try {
            File file = new File("F:\\IdeaProjects\\Einkaufsliste\\liste2");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                liste.add(new Artikel(Integer.parseInt(split[0]), split[1]));
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen: " + e.getMessage());
        }

        liste.listeAusgeben();
    }

}
