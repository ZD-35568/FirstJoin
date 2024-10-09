package pl.coderslab.mysql.javamysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField inputField, outputField;
    private JButton browseInput, browseOutput, generateButton;
    private JLabel inputLabel, outputLabel;
    private JPanel panel;

    public Panel(boolean resible) {
        this.setSize(1000, 2000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Block Divider");
        this.setResizable(resible);

        // Initialize components
        inputLabel = new JLabel("Input File: ");
        inputField = new JTextField(20);
        browseInput = new JButton("Browse");
        outputLabel = new JLabel("Output File: ");
        outputField = new JTextField(20);
        browseOutput = new JButton("Browse");
        generateButton = new JButton("Generate");

        // Add action listeners
        browseInput.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                inputField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });
        browseOutput.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                outputField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });
        generateButton.addActionListener(e -> {
            String inputFilePath = inputField.getText();
            String outputFilePath = outputField.getText();

            if (inputFilePath.isEmpty() || outputFilePath.isEmpty()) {
                System.out.println("Please select input and output files.");
                return;
            }
            splitAndSaveFile(inputFilePath, outputFilePath);

//            try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//                 BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    line = line.trim();
//                    for (int i = 0; i < line.length(); i += 22) {
//                        String block = line.substring(i, Math.min(i + 22, line.length()));
//                        bw.write(block);
//                        bw.newLine();
//                    }
//                    bw.newLine();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }

            System.out.println("Done.");
        });

        // Create panel and add components
        panel = new JPanel();
        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(browseInput);
        panel.add(outputLabel);
        panel.add(outputField);
        panel.add(browseOutput);
        panel.add(generateButton);

        // Add panel to frame
        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Panel(true);
    }

    public static void splitAndSaveFile(String inputPath, String outputPath) {
        try {
            // Tworzenie BufferedReader do odczytu pliku wejściowego
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            // Tworzenie BufferedWriter do zapisu pliku wyjściowego
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            // Tworzenie zmiennej przechowującej aktualnie odczytany wiersz tekstu
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Zastępowanie znaków nowej linii spacją
                sb.append(line.replaceAll("\\n", " "));
            }
            char[] text = sb.toString().toCharArray();

            StringBuilder command = new StringBuilder();
            for (int i = 0,j = 0; i <= text.length - 1; i++, j++){
                if (j >= 39){
                    command.append("==========");
                    j = 0;
                }
                command.append(text[i]);
            }
            writer.write(command.toString());
            // Zamykanie BufferedReader i BufferedWriter
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}