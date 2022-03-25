package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class SplashScreenWorld {

    JFrame frame;
    JProgressBar bar;
    JPanel framePanel;

    public SplashScreenWorld() {
        frame = new JFrame();
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);

        framePanel = new JPanel();
        framePanel.setLayout(new GridLayout(2, 1));

        ImageIcon img = new ImageIcon("./data/tobs.jpg");
        Image nimg = img.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        img = new ImageIcon(nimg);
        JLabel image = new JLabel(img);
        framePanel.add(image);

        bar = new JProgressBar();
        bar.setSize(200, 50);
        bar.setValue(0);
        framePanel.add(bar);

        frame.add(framePanel);
        frame.setVisible(true);

        runningProgress();
    }

    public void imgSetUp() {
        //img.setSize(40, 40);


    }

    public void runningProgress() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bar.setValue(i);
        }
        frame.dispose();
    }
}
