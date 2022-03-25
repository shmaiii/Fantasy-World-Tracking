package ui;

import model.FantasyWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

// A class that represents options inside view-world
public class ViewWorld extends JFrame {

    protected JFrame frame;
    protected JMenuBar menu;
    protected WorldApp myWorld;

    //EFFECTS: construct object to lay out gui of view-world option
    public ViewWorld(WorldApp myWorld) {
        this.myWorld = myWorld;

        frame = new JFrame();
        setupFrame();

        menu = new JMenuBar();
        JButton allWorld = new JButton("All Worlds");
        buttonProperties(allWorld);
        allWorld.addActionListener(new AllWorldAction());

        JButton fav = new JButton("Favourites");
        fav.addActionListener(new SubListAction(myWorld.getWorld().getFav()));
        buttonProperties(fav);

        JButton beenTo = new JButton("Been To");
        beenTo.addActionListener(new SubListAction(myWorld.getWorld().getBeenTo()));
        buttonProperties(beenTo);

        JButton wantTo = new JButton("Want To");
        wantTo.addActionListener(new SubListAction(myWorld.getWorld().getWantTo()));
        buttonProperties(wantTo);

        JButton backtoHomePage = new JButton("Home Page");
        backtoHomePage.addActionListener(new HomePageAction());
        buttonProperties(backtoHomePage);

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set properties for frame
    void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(myWorld.getWidth(), myWorld.getHeight());
        frame.setBackground(new Color(215, 196, 238));
    }

    //MODIFIES: this
    // EFFECTS: set up properties for button
    public void buttonProperties(JButton button) {
        button.setBackground(new Color(111, 90, 137));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        menu.add(button);
    }

    //MODIFIES: this
    //EFFECTS: properties for JList
    public void jlistProperties(JList list) {
        list.setBackground(new Color(215, 196, 238));
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        list.setFixedCellHeight(50);
        list.setFont(new Font("Times New Roman", Font.BOLD, 30));
    }

    //a class that represents going back to homepage
    private class HomePageAction implements ActionListener {

        // MODIFIES: this
        //EFFECTS: return to homepage
        @Override
        public void actionPerformed(ActionEvent e) {
            new HomePage(myWorld);
        }
    }

    // a class that represents the function of all world button
    private class AllWorldAction implements ActionListener {
        JList list;
        DefaultListModel listModel;
        JButton deleteButton;
        JButton markAsButton;
        JPanel buttonPanel;


        @Override
        // EFFECTS: lay the list of all worlds to gui
        // create a panel for each world, with appropriate 2 buttons
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            JPanel framePanel = new JPanel();
            framePanel.setLayout(new BorderLayout());

            listModel = new DefaultListModel();
            for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
                listModel.addElement(fw.getName());
            }
            list = new JList(listModel);
            jlistProperties(list);
            JScrollPane scrollList = new JScrollPane(list);

            buttonPanel = new JPanel();
            createButtons();

            framePanel.add(scrollList);
            framePanel.add(buttonPanel, BorderLayout.PAGE_END);
            frame.add(framePanel);
            frame.setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: create 2 buttons markAs and delete
        void createButtons() {
            buttonPanel.setLayout(new FlowLayout());
            markAsButton = new JButton("Mark As");
            markAsButton.addActionListener(new MarkAsAction());
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new DeleteAction());
            setupButtons(markAsButton);
            setupButtons(deleteButton);
        }

        // MODIFIES: this
        // EFFECTS: setup button properties for button
        void setupButtons(JButton button) {
            button.setBackground(new Color(111, 90, 137));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
            buttonPanel.add(button);
        }

        // a class that presents the markAs action
        private class MarkAsAction implements ActionListener, ItemListener {

            JCheckBox favButton;
            JCheckBox beenToButton;
            JCheckBox wantToButton;
            FantasyWorld chosen;

            // MODIFIES: this
            // EFFECTS: prompt 3 choices of either fav, beenTo, wantTo, then mark the world as chosen state.
            // can choose only one at one time
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                favButton = new JCheckBox("Fav");
                favButton.addItemListener(this);
                beenToButton = new JCheckBox("Been To");
                beenToButton.addItemListener(this);
                wantToButton = new JCheckBox("Want To");
                wantToButton.addItemListener(this);

                popupMenu.add(favButton);
                popupMenu.add(beenToButton);
                popupMenu.add(wantToButton);
                popupMenu.show(markAsButton, markAsButton.getX(), markAsButton.getY());

                int index = list.getSelectedIndex();
                String worldName = (String) listModel.get(index);
                for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
                    if (fw.getName().equals(worldName)) {
                        chosen = fw;
                    }
                }
            }

            // MODIFIES: this
            // EFFECTSl based on the option chosen of the checkbox, add the appropriate world to corresponding sublist
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getItemSelectable();

                if (source == favButton) {
                    myWorld.getWorld().add(myWorld.getWorld().getFav(), chosen);
                }
                if (source == beenToButton) {
                    myWorld.getWorld().add(myWorld.getWorld().getBeenTo(), chosen);
                }
                if (source == wantToButton) {
                    myWorld.getWorld().add(myWorld.getWorld().getWantTo(), chosen);
                }
            }
        }

        // a class that represents the delete action of delete button
        private class DeleteAction implements ActionListener {

            //MODIFIES: this
            //EFFECTS: delete the selected world from the world state, laying out on the gui.
            // Disable button if list is empty
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String worldName = (String) listModel.get(index);
                for (FantasyWorld fw : myWorld.getWorld().getAllWorld()) {
                    if (fw.getName().equals(worldName)) {
                        myWorld.getWorld().deleteWorld(fw);
                    }
                }
                listModel.remove(index);

                if (listModel.size() == 0) {
                    deleteButton.setEnabled(false);
                }
            }
        }
    }

    // a class that represents the function of sublist button
    private class SubListAction implements ActionListener {

        DefaultListModel listModel;
        JList list;
        List<FantasyWorld> sublist;
        JButton removeButton;

        //EFFECTS: construct a sublistaction obj with specified sublist
        public SubListAction(List<FantasyWorld> sublist) {
            this.sublist = sublist;
        }

        @Override
        // MODIFIES: this
        //EFFECTS: lay the sublists to gui
        public void actionPerformed(ActionEvent e) {
            layout(sublist);
            frame.setVisible(true);
        }

        // EFFECTS: helper method that determines how sublists should be laid out
        public void layout(List<FantasyWorld> sublist) {
            frame.getContentPane().removeAll();
            JPanel panelForFrame = new JPanel();
            panelForFrame.setLayout(new BorderLayout());

            listModel = new DefaultListModel();
            for (FantasyWorld fw : sublist) {
                listModel.addElement(fw.getName());
            }
            list = new JList(listModel);
            jlistProperties(list);
            JScrollPane scrollList = new JScrollPane(list);
            panelForFrame.add(scrollList);

            JPanel buttonPanel = new JPanel();
            removeButton = new JButton("Remove");
            removeButton.setBackground(new Color(111, 90, 137));
            removeButton.setForeground(Color.WHITE);
            removeButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            removeButton.addActionListener(new RemoveAction());
            buttonPanel.add(removeButton);

            panelForFrame.add(buttonPanel, BorderLayout.PAGE_END);

            frame.add(panelForFrame);
        }

        // a class representing the remove action for remove buttons
        private class RemoveAction implements ActionListener {

            // MODIFIES: this
            // EFFECTS: remove the world from according sublists when the button is pressed, if listModel is empty
            // then disable the button
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String worldName = (String) listModel.get(index);
                listModel.remove(index);
                for (FantasyWorld fw : sublist) {
                    if (fw.getName().equals(worldName)) {
                        myWorld.getWorld().remove(sublist, fw);
                    }
                }

                if (listModel.size() == 0) {
                    removeButton.setEnabled(false);
                }
            }
        }
    }

}

