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
        setupFrame();

        JPanel framePanel = new JPanel();
        setupMenuBar();
        framePanel.setLayout(new GridLayout(2, 1));

        sentences = new JPanel();
        sentences.setBackground(new Color(215, 196, 238));
        sentences.setLayout(new GridLayout(2, 1));

        Label label1 = new Label("Welcome to my Fantasy World!", Label.CENTER);
        label1.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 50));
        sentences.add(label1);
        Label label2 = new Label("Let's create our own adventure!", Label.CENTER);
        label2.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 50));
        sentences.add(label2);

        buttons = new JPanel();
        buttons.setBackground(new Color(215, 196, 238));
        setupButtons();

        framePanel.add(sentences);
        framePanel.add(buttons);
        frame.add(framePanel);
        frame.setVisible(true);

    }

    // MODIFIES: this
    //EFFECTS: set up frame for GUI
    void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(myWorld.getWidth(), myWorld.getHeight());
        frame.setTitle("My Fantasy World");
    }

    //MODIFIES: this
    //EFFECTS: set up the menuBar with load and save buttons
    void setupMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JButton load = new JButton("Load World");
        setMenuButton(load);
        load.addActionListener(new DataAction("load"));
        JButton save = new JButton("Save World");
        setMenuButton(save);
        save.addActionListener(new DataAction("save"));
        menubar.add(load);
        menubar.add(save);
        frame.setJMenuBar(menubar);
    }

    // MODIFIES: this
    // EFFECTS: set properties for menu buttons
    void setMenuButton(JButton button) {
        button.setBackground(new Color(111, 90, 137));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }

    //MODIFIES: this
    // EFFECTS: set up buttons for action choices
    void setupButtons() {
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton viewWorld = new JButton("View world");
        viewWorld.setActionCommand("view-world");
        viewWorld.addActionListener(new ViewAction());

        JButton viewCategory = new JButton("View category");
        viewCategory.setActionCommand("view-cat");
        viewCategory.addActionListener(new ViewAction());

        JButton create = new JButton("Create");
        create.addActionListener(new CreateAction());

        buttonProperties(viewWorld);
        buttonProperties(viewCategory);
        buttonProperties(create);

    }

    //MODIFIES: this
    //EFFECTS: style the buttons
    void buttonProperties(JButton b) {
        b.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b.setForeground(Color.WHITE);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBorderPainted(true);
        b.setBackground(new Color(111, 90, 137));
        buttons.add(b);
        buttons.add(Box.createVerticalGlue());
    }

    // represents change frame function
    private class ViewAction implements ActionListener {

        //MODIFIES: this
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

        // EFFECTS: construct a dataAction obj with specified command
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

