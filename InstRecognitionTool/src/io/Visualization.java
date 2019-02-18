package io;

import model.AudioFile;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.io.IOException;

public class Visualization {

    public static void main(String[] args) throws IOException {
        AudioFile audioFile = new AudioFile("D:\\College\\Soft Computing\\Data\\IRMAS-TrainingData\\vio\\[vio][cla]2083__1.wav");
        Visualization vis = new Visualization();
        vis.visualize(audioFile);
    }

    public void visualize(AudioFile audioFile) {
        Plot2DPanel panel = new Plot2DPanel();
        panel.addScatterPlot(audioFile.getFilename(), audioFile.getFft());
        JFrame frame = new JFrame("Scatter");
        frame.setContentPane(panel);
        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
