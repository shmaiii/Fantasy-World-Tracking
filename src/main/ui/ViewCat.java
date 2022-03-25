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
        JButton cartoon = new JButton("CARTOON");
        cartoon.setActionCommand("cartoon");
        buttonProperties(cartoon);

        //frame.getRootPane().setDefaultButton(allWorld);
        JButton book = new JButton("BOOK");
        book.setActionCommand("book");
        buttonProperties(book);
        JButton movie = new JButton("MOVIE");
        movie.setActionCommand("movie");
        buttonProperties(movie);
        JButton game = new JButton("GAME");
        game.setActionCommand("game");
        buttonProperties(game);

        JButton backtoHomePage = new JButton("Home Page");
        backtoHomePage.addActionListener(new HomePageAction());
        backtoHomePage.setBackground(new Color(111, 90, 137));
        backtoHomePage.setForeground(Color.WHITE);
        menu.add(backtoHomePage);

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    public void buttonProperties(JButton button) {
        menu.add(button);
        button.setBackground(new Color(111, 90, 137));
        button.setForeground(Color.WHITE);
        button.addActionListener(new ViewCatListAction());
    }

    private class ViewCatListAction implements ActionListener {

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

        public void layout(List<FantasyWorld> low) {
            frame.getContentPane().removeAll();

            DefaultListModel listModel = new DefaultListModel();
            for (FantasyWorld fw : low) {
                listModel.addElement(fw.getName());
            }
            JList list = new JList(listModel);
            list.setLayoutOrientation(JList.VERTICAL);
            list.setBackground(new Color(215, 196, 238));

            frame.add(list);
        }
    }

    //a class that represents going back to homepage
    private class HomePageAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new HomePage(myWorld);
        }
    }
}
