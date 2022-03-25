package ui;

import model.FantasyWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.Category.*;
import static model.Category.MOVIE;

// A class that represents homepage
public class HomePage extends JFrame {
    WorldApp myWorld;
    JPanel buttons;
    JPanel sentences;
    JFrame frame;

    //EFFECTS: construct object that represents homepage
    public HomePage(WorldApp w) {
        frame = new JFrame();

        this.myWorld = w;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(myWorld.getWidth(), myWorld.getHeight());
        JPanel framePanel = new JPanel();
        setupMenuBar();
        framePanel.setLayout(new GridLayout(2, 1));

        sentences = new JPanel();
        sentences.setLayout(new GridLayout(2, 1));

        Label label1 = new Label("Welcome to my Fantasy World!", Label.CENTER);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        sentences.add(label1);
        Label label2 = new Label("Let's create our own adventure!", Label.CENTER);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        sentences.add(label2);

        buttons = new JPanel();
        setupButtons();

        framePanel.add(sentences);
        framePanel.add(buttons);
        frame.add(framePanel);
        frame.setTitle("My Fantasy World");
        frame.setVisible(true);
    }

    void setupMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JButton load = new JButton("Load World");
        load.addActionListener(new DataAction("load"));
        JButton save = new JButton("Save World");
        save.addActionListener(new DataAction("save"));
        menubar.add(load);
        menubar.add(save);
        frame.setJMenuBar(menubar);
    }

    void setupButtons() {
        buttons.setLayout(new GridLayout(3, 1));
        JButton viewWorld = new JButton("View world");
        viewWorld.setActionCommand("view-world");
        viewWorld.addActionListener(new ViewAction());

        JButton viewCategory = new JButton("View category");
        viewCategory.setActionCommand("view-cat");
        viewCategory.addActionListener(new ViewAction());

        JButton create = new JButton("Create");
        create.addActionListener(new CreateAction());
        buttons.add(viewWorld);
        buttons.add(viewCategory);
        buttons.add(create);
    }

    // represents change frame function
    private class ViewAction implements ActionListener {

        // EFFECTS: based on the actionCommand, go to appropriate submenu's frame
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("view-world")) {
                new ViewWorld(myWorld);
            } else if (e.getActionCommand().equals("view-cat")) {
                new ViewCat(myWorld);
            }
        }
    }

    // represents action when create button is clicked
    private class CreateAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: prompt the user an dialogue to enter name and choose category for new world, then store a new world
        // to the world state
        @Override
        public void actionPerformed(ActionEvent e) {
            String worldName = JOptionPane.showInputDialog(frame, "Enter the Fantasy World Name");
            Object[] options = {"CARTOON", "GAME", "MOVIE", "BOOK"};
            int n = JOptionPane.showOptionDialog(frame, "Pick a category for your world", "Category",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            String cat = (String) options[n];

            if (cat.equals("CARTOON")) {
                myWorld.getWorld().storeWorld(new FantasyWorld(worldName, CARTOON));
            } else if (cat.equals("GAME")) {
                myWorld.getWorld().storeWorld(new FantasyWorld(worldName, GAME));
            } else if (cat.equals("MOVIE")) {
                myWorld.getWorld().storeWorld(new FantasyWorld(worldName, MOVIE));
            } else if (cat.equals("BOOK")) {
                myWorld.getWorld().storeWorld(new FantasyWorld(worldName, BOOK));
            }

            JOptionPane.showMessageDialog(frame, worldName + " has been successfully created!");

        }
    }

    // a class that represent load action to load data
    private class DataAction implements ActionListener {

        String command;

        public DataAction(String command) {
            this.command = command;
        }

        //MODIFIES: this
        //EFFECTS: load data from file to world
        @Override
        public void actionPerformed(ActionEvent e) {
            if (command.equals("load")) {
                myWorld.loadWorldState();
            }
            if (command.equals("save")) {
                myWorld.saveWorldState();
            }
        }
    }
}

