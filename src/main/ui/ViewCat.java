package ui;

import model.FantasyWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//a class that represents gui of category viewing options
public class ViewCat extends JFrame {
    protected JFrame frame;
    protected JMenuBar menu;
    protected WorldApp myWorld;

    //EFFECTS: construct object to lay out gui of view-world option
    public ViewCat(WorldApp myWorld) {
        this.myWorld = myWorld;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(myWorld.getWidth(), myWorld.getHeight());

        menu = new JMenuBar();
        addButtons();

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: add buttons
    void addButtons() {
        JButton cartoon = new JButton("CARTOON");
        cartoon.setActionCommand("cartoon");
        buttonProperties(cartoon);
        cartoon.addActionListener(new ViewCatListAction());

        JButton book = new JButton("BOOK");
        book.setActionCommand("book");
        buttonProperties(book);
        book.addActionListener(new ViewCatListAction());

        JButton movie = new JButton("MOVIE");
        movie.setActionCommand("movie");
        buttonProperties(movie);
        movie.addActionListener(new ViewCatListAction());

        JButton game = new JButton("GAME");
        game.setActionCommand("game");
        buttonProperties(game);
        game.addActionListener(new ViewCatListAction());

        JButton backtoHomePage = new JButton("HomePage");
        buttonProperties(backtoHomePage);
        backtoHomePage.addActionListener(new HomePageAction());
    }

    // MODIFIES: this
    //EFFECTS: set up properties for buttons
    public void buttonProperties(JButton button) {
        menu.add(button);
        button.setBackground(new Color(111, 90, 137));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }

    // a class that represents the action to view category
    private class ViewCatListAction implements ActionListener {

        // MODIFIES: this
        //EFFECTS: layout lists based on action commands of buttons
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("book")) {
                layout(myWorld.getWorld().getBook());
            } else if (e.getActionCommand().equals("movie")) {
                layout(myWorld.getWorld().getMovie());
            } else if (e.getActionCommand().equals("game")) {
                layout(myWorld.getWorld().getGame());
            } else if (e.getActionCommand().equals("cartoon")) {
                layout(myWorld.getWorld().getCartoon());
            }
            frame.setVisible(true);
        }

        // MODIFIES: this
        //EFFECTS: lay out JList to GUI
        public void layout(List<FantasyWorld> low) {
            frame.getContentPane().removeAll();

            DefaultListModel listModel = new DefaultListModel();
            for (FantasyWorld fw : low) {
                listModel.addElement(fw.getName());
            }
            JList list = new JList(listModel);
            list.setLayoutOrientation(JList.VERTICAL);
            list.setVisibleRowCount(-1);
            list.setBackground(new Color(215, 196, 238));
            list.setFixedCellHeight(50);
            list.setFont(new Font("Times New Roman", Font.BOLD,30));

            frame.add(list);
        }
    }

    //a class that represents going back to homepage
    private class HomePageAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: return to homepage
        @Override
        public void actionPerformed(ActionEvent e) {
            new HomePage(myWorld);
        }
    }
}
