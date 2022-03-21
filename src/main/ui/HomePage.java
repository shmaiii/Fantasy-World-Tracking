package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A class that represents homepage
public class HomePage extends JFrame {
    WorldApp myWorld;
    JPanel buttons;

    //EFFECTS: construct object that represents homepage
    public HomePage(WorldApp w) {
        this.myWorld = w;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(2, 1));

        JPanel sentences = new JPanel();
        sentences.setLayout(new GridLayout(2, 1));
        //JTextArea text1 = new JTextArea("Welcome to my Fantasy World!");
        Label label1 = new Label("Welcome to my Fantasy World!", Label.CENTER);
        sentences.add(label1);
        Label label2 = new Label("Let's create our own adventure!", Label.CENTER);
        sentences.add(label2);

        buttons = new JPanel();
        setupButtons();

        add(sentences);
        add(buttons);
        setTitle("My Fantasy World");
        setVisible(true);
    }

    void setupButtons() {
        buttons.setLayout(new GridLayout(2, 2));
        JButton viewWorld = new JButton("View world");
        viewWorld.setActionCommand("view-world");
        viewWorld.addActionListener(new ViewAction());

        JButton viewCategory = new JButton("View category");
        viewCategory.setActionCommand("view-cat");
        viewCategory.addActionListener(new ViewAction());

        JButton create = new JButton("Create");
        JButton exit = new JButton("Exit");
        buttons.add(viewWorld);
        buttons.add(viewCategory);
        buttons.add(create);
        buttons.add(exit);
    }

    // represents change frame function
    private class ViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("view-world")) {
                new ViewWorld(myWorld);
            } else if (e.getActionCommand().equals("view-cat")) {
                new ViewCat(myWorld);
            }
        }
    }


}
