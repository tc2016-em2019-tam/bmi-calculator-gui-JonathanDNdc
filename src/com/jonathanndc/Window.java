package com.jonathanndc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;

public class Window extends JFrame {

    private ButtonGroup unitGroup = new ButtonGroup();
    private JTextField heightTxtFld = new JTextField();
    private JTextField weightTxtFld = new JTextField();
    private JLabel bmiLbl = new JLabel("Your BMI:");
    private JLabel bodyTypeLbl = new JLabel("Your body type:");

    public Window() {
        windowSetup();
        setComponents();
        this.pack();
        this.setVisible(true);
    }

    private void windowSetup() {
        this.setTitle("BMI");
        this.setLayout(new GridLayout(5, 2));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setComponents() {
        JLabel heightLbl = new JLabel("Your Height (cm):");
        JLabel weightLbl = new JLabel("Your Weight (kg):");

        JRadioButton metricRdBtn = new JRadioButton("Metric");
        JRadioButton imperialRdBtn = new JRadioButton("Imperial");
        metricRdBtn.setActionCommand("Metric");
        imperialRdBtn.setActionCommand("Imperial");

        unitGroup.add(metricRdBtn);
        unitGroup.add(imperialRdBtn);

        metricRdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightLbl.setText("Your Height (cm):");
                weightLbl.setText("Your Weight (kg):");
                updateResults();
            }
        });

        imperialRdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightLbl.setText("Your Height (in):");
                weightLbl.setText("Your Weight (lb):");
                updateResults();
            }
        });


        metricRdBtn.setSelected(true);

        JButton computeBtn = new JButton("Compute BMI");
        computeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateResults();
            }
        });

        JButton cleanBtn = new JButton("Clean Screen");
        cleanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanScreen();
            }
        });


        this.add(heightLbl);
        this.add(heightTxtFld);
        this.add(weightLbl);
        this.add(weightTxtFld);
        this.add(metricRdBtn);
        this.add(imperialRdBtn);
        this.add(bmiLbl);
        this.add(bodyTypeLbl);
        this.add(computeBtn);
        this.add(cleanBtn);
    }

    private double calculateBMI(double height, double weight) {
        if (unitGroup.getSelection().getActionCommand().equals("Metric")) {
            return weight / (height * height) * 10000;
        } else {
            return weight * 703 / (height * height);
        }
    }

    private void updateResults() {
        String bodyType;
        double bmi = calculateBMI(parseDouble(heightTxtFld.getText()), parseDouble(weightTxtFld.getText()));
        bmiLbl.setText("Your BMI: " + String.format("%.2f", bmi));

        if (bmi < 18.5) {
            bodyType = "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            bodyType = "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            bodyType = "Overweight";
        } else if (bmi >= 30 && bmi < 40) {
            bodyType = "Obesity";
        } else {
            bodyType = "Morbid obesity";
        }

        bodyTypeLbl.setText("Your body type: " + bodyType);
        this.pack();
    }

    private void cleanScreen() {
        heightTxtFld.setText("");
        weightTxtFld.setText("");
        bmiLbl.setText("Your BMI:");
        bodyTypeLbl.setText("Your body type:");
        this.pack();
    }
}
