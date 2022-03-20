package ui;

import model.FantasyWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A class that represents options inside view-world
public class ViewWorld extends JFrame {
    protected JFrame frame;
    protected JMenuBar menu;
    protected WorldApp myWorld;

    //EFFECTS: construct object to lay out gui of view-world option
    public ViewWorld(WorldApp myWorld) {
        this.myWorld = myWorld;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        menu = new JMenuBar();
        JButton allWorld = new JButton("All Worlds");
        buttonProperties(allWorld);
        allWorld.addActionListener(new AllWorldAction());
        //frame.getRootPane().setDefaultButton(allWorld);
        JButton fav = new JButton("Favourites");
        buttonProperties(fav);
        JButton beenTo = new JButton("Been To");
        buttonProperties(beenTo);
        JButton wantTo = new JButton("Want To");
        buttonProperties(wantTo);

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    public void buttonProperties(JButton button) {
        menu.add(button);
    }

    // a class that represents the function of all world button
    private class AllWorldAction implements ActionListener {

        @Override
        // EFFECTS: lay the list of all worlds to gui
        // create a panel for each world, with appropriate 2 buttons
        public void actionPerformed(ActionEvent e) {
            for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                Label name = new Label(fw.getName());
                JButton markAs = new JButton("Mark As");
                JButton delete = new JButton("delete");
                panel.add(name);
                panel.add(markAs);
                panel.add(delete);
                frame.add(panel);
            }
        }
    }

    // a class that represents the function of sublist button
    private class SubListAction implements ActionListener {

        @Override
        //EFFECTS: lay the sublists to gui
        public void actionPerformed(ActionEvent e) {
            //
        }
    }

}

