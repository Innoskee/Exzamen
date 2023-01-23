package gui.panels;

import javax.swing.*;
import java.awt.*;

public class FunctionsPanel extends JPanel {

    public JLabel explicitLabel = new JLabel("Explicit function: ");
    public JLabel parametricLabel = new JLabel("Parametric function: ");
    public JCheckBox explicitCheck = new JCheckBox();
    public JCheckBox parametricCheck = new JCheckBox();

    public FunctionsPanel(){
        explicitCheck.setSelected(true);
        parametricCheck.setSelected(true);
        setupLayout();
    }

    private void setupLayout(){
        GroupLayout gl = new GroupLayout(this);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addComponent(explicitLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(explicitCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(20)
                .addComponent(parametricLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(parametricCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(10)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addGap(10)
                        .addComponent(explicitLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(explicitCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(parametricLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(parametricCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(10)
                )
                .addGap(10)
        );
        setLayout(gl);
    }
}