package me.alpine.bootstrapper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BootstrapperGui extends JFrame {
    public BootstrapperGui() {
        setTitle("Alpine Bootstrapper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(0x121212));
    }

    public void run(String[] args) {
        final JLabel label = new JLabel("Checking Alpine ...");
        label.setForeground(Color.WHITE);
        add(label);
        setVisible(true);

        int status = checkAlpineJar();

        if (status == 0) {
            label.setText("Alpine is up to date.     ");
        } else {
            label.setText("Alpine could not be updated.     ");
        }

        final JButton doneButton = new JButton("Launch Alpine !");
        doneButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        doneButton.setBackground(Color.WHITE);
        doneButton.setForeground(Color.BLACK);
        doneButton.setFocusPainted(false);

        doneButton.addActionListener(e -> {
            dispose();
            net.minecraft.client.main.Main.main(args);
        });
        add(doneButton);

        setVisible(true);
    }

    private int checkAlpineJar() {
        try {
            final long currentSize = Files.size(Paths.get(Helper.getAlpineJarPath()));
            final long serverSize = Helper.getFileSize(new URL("https://github.com/Alpine-Minecraft/alpine-bootstrapper/raw/main/Alpine.jar"));

            if (currentSize != serverSize) {
                return 1;
            } else {
                return 0;
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
