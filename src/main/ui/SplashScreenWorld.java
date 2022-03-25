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
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon("./data/travelSplashIcon.jpg");
        Image nimg = img.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        img = new ImageIcon(nimg);
        JLabel image = new JLabel(img);
        framePanel.add(image);

        bar = new JProgressBar();
        JPanel barPanel = new JPanel();
        barPanel.add(bar);
        framePanel.add(barPanel, BorderLayout.PAGE_END);

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
