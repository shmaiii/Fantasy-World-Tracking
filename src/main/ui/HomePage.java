package ui;

import javax.swing.*;
import java.awt.*;

// A class that represents homepage
public class HomePage extends JFrame {
    WorldApp myWorld;

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

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 2));
        JButton viewWorld = new JButton("View world");
        JButton viewCategory = new JButton("View category");
        JButton create = new JButton("Create");
        JButton exit = new JButton("Exit");
        buttons.add(viewWorld);
        buttons.add(viewCategory);
        buttons.add(create);
        buttons.add(exit);

        add(sentences);
        add(buttons);
        setTitle("My Fantasy World");
        setVisible(true);
    }


}
