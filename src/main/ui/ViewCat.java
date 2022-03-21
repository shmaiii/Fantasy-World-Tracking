package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setSize(600, 600);

        menu = new JMenuBar();
        JButton cartoon = new JButton("CARTOON");
        buttonProperties(cartoon);

        //frame.getRootPane().setDefaultButton(allWorld);
        JButton book = new JButton("BOOK");
        buttonProperties(book);
        JButton movie = new JButton("MOVIE");
        buttonProperties(movie);
        JButton game = new JButton("GAME");
        buttonProperties(game);

        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }

    public void buttonProperties(JButton button) {
        menu.add(button);
    }

    private class ViewCatListAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
}
