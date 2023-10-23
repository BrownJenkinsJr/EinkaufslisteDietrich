import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI extends JFrame implements ActionListener {

    private final JList<String> jlist;
    private JTextField artikelName;
    private Einkaufsliste einkaufsliste = new Einkaufsliste();

    public GUI() {
        JMenuBar menubar = new JMenuBar();
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        menubar.add(open);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        menubar.add(save);
        setJMenuBar(menubar);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(4, 4));

        jlist = new JList<String>(einkaufsliste);
        content.add(BorderLayout.CENTER, new JScrollPane(jlist));

        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        controls.add(artikelName = new JTextField());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        controls.add(addButton);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        controls.add(removeButton);

        content.add(BorderLayout.PAGE_END, controls);

        setContentPane(content);

        setSize(400, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean open = e.getActionCommand().equals("Open");
        switch (e.getActionCommand()) {
            case "Add" -> {
                einkaufsliste.add(new Artikel(1, artikelName.getText()));
                jlist.updateUI();
            }
            case "Remove" -> {
                einkaufsliste.remove(jlist.getSelectedIndex());
                jlist.updateUI();
            }
            case "Open", "Save" -> {
                FileDialog fd = new FileDialog(this, "Choose a file", open ? FileDialog.LOAD : FileDialog.SAVE);
                fd.setVisible(true);
                if (fd.getFile() == null)
                    return;

                if (open) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fd.getFile()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] split = line.split(",");
                            einkaufsliste.add(new Artikel(Integer.parseInt(split[0]), split[1]));
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fd.getFile()))) {
                        einkaufsliste.getList().toFirst();
                        while (einkaufsliste.getList().hasAccess()) {
                            Artikel content = einkaufsliste.getList().getContent();
                            writer.write(content.getAnzahl() + "," + content.getName());
                            writer.newLine();
                            einkaufsliste.getList().next();
                        }
                        writer.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                jlist.updateUI();
            }
        }
    }
}
