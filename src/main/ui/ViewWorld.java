package ui;

import model.FantasyWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// A class that represents options inside view-world
public class ViewWorld extends JFrame {
    private static int WIDTH = 600;
    private static int HEIGHT = 600;

    protected JFrame frame;
    protected JMenuBar menu;
    protected WorldApp myWorld;

    //EFFECTS: construct object to lay out gui of view-world option
    public ViewWorld(WorldApp myWorld) {
        this.myWorld = myWorld;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        menu = new JMenuBar();
        JButton allWorld = new JButton("All Worlds");
        buttonProperties(allWorld);
        allWorld.addActionListener(new AllWorldAction());
        //frame.getRootPane().setDefaultButton(allWorld);
        JButton fav = new JButton("Favourites");
        fav.setActionCommand("fav");
        fav.addActionListener(new SubListAction());
        buttonProperties(fav);

        JButton beenTo = new JButton("Been To");
        beenTo.setActionCommand("beenTo");
        beenTo.addActionListener(new SubListAction());
        buttonProperties(beenTo);

        JButton wantTo = new JButton("Want To");
        wantTo.setActionCommand("wantTo");
        wantTo.addActionListener(new SubListAction());
        buttonProperties(wantTo);

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    public void buttonProperties(JButton button) {
        menu.add(button);
    }

    // a class that represents the function of all world button
    private class AllWorldAction implements ActionListener {
        //JList list;

        @Override
        // EFFECTS: lay the list of all worlds to gui
        // create a panel for each world, with appropriate 2 buttons
        public void actionPerformed(ActionEvent e) {
//            DefaultListModel listModel = new DefaultListModel();
//            for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
//                listModel.addElement(fw);
//            }
//            list = new JList(listModel);
            //frame.add(list);
            frame.getContentPane().removeAll();
            frame.setLayout(new GridLayout(myWorld.getWorld().getAllWorld().size(), 3));
            for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                Label world = new Label(fw.getName());
                JButton markAs = new JButton("Mark As");
                JButton delete = new JButton("Delete");
                panel.add(world);
                panel.add(markAs);
                panel.add(delete);
                frame.add(panel);
            }
            frame.setVisible(true);
        }

        private class MarkAs implements ActionListener {

            // MODIFIES: this
            // EFFECTS: prompt 3 choices of either fav, beenTo, wantTo, then mark the world as chosen state.
            // can choose only one at one time
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        }

        private class Delete implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        }
    }

    // a class that represents the function of sublist button
    private class SubListAction implements ActionListener {

        @Override
        //EFFECTS: lay the sublists to gui
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("fav")) {
                layout(myWorld.getWorld().getFav());
            } else if (e.getActionCommand().equals("beenTo")) {
                layout(myWorld.getWorld().getBeenTo());
            } else if (e.getActionCommand().equals("wantTo")) {
                layout(myWorld.getWorld().getWantTo());
            }
            frame.setVisible(true);
        }

        // EFFECTS: helper method that determines how sublists should be laid out
        public void layout(List<FantasyWorld> sublist) {
            frame.getContentPane().removeAll();
            frame.setLayout(new GridLayout(sublist.size(), 2));
            for (FantasyWorld fw : sublist) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                Label world = new Label(fw.getName());
                JButton remove = new JButton("Remove");
                panel.add(world);
                panel.add(remove);
                frame.add(panel);
            }
        }

        // a class representing the remove action for remove buttons
        private class RemoveAction implements ActionListener {

            // MODIFIES: this
            // EFFECTS: remove the world from according sublists when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        }
    }

}

