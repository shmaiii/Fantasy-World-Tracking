package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

// a class that represent the splashscreen
public class SplashScreenWorld {

    JFrame frame;
    JProgressBar bar;
    JPanel framePanel;

    //EFFECTS: construct the splashscreen project
    public SplashScreenWorld() {
        frame = new JFrame();
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);

        framePanel = new JPanel();
        framePanel.setLayout(new GridLayout(2, 1));

        ImageIcon img = new ImageIcon("./data/travelSplashIcon.jpg");
        Image nimg = img.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        img = new ImageIcon(nimg);
        JLabel image = new JLabel(img);
        framePanel.add(image);

        bar = new JProgressBar();
        framePanel.add(bar);

        frame.add(framePanel);
        frame.setVisible(true);

        runningProgress();
    }

    // MODIFIES: this
    // EFFECTS: make the progress bar runs to express the splash screen, splash screen ends when the progress bar ends
    public void runningProgress() {
        for (int i = 0; i <= 50; i++) {
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
