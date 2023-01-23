package gui;

import function.ExplFunction;
import function.ParamFunction;
import gui.convertion.CartScreenPlane;
import gui.painters.CartesianPainter;
import gui.painters.FunctionPainter;
import gui.panels.ControlPanel;
import gui.panels.FunctionsPanel;
import gui.panels.GraphicsPanel;
import gui.panels.ISpinnerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {
    public final GraphicsPanel graphicsPanel = new GraphicsPanel();
    public final ControlPanel controlPanel = new ControlPanel();
    public final FunctionsPanel functionsPanel = new FunctionsPanel();
    public final CartScreenPlane plane;
    private final FunctionPainter explicitPainter;
    private final FunctionPainter parametricPainter;

    public MainFrame(int width, int height){
        Dimension minFrameSize = new Dimension(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(minFrameSize);
        setTitle("Exzamen");

        setupLayoutAndPack();
        plane = getNewCartScreenPlane(graphicsPanel.getWidth(), graphicsPanel.getHeight());
        explicitPainter = new FunctionPainter(plane).setFunction(new ExplFunction()).setColor(Color.RED);
        parametricPainter = new FunctionPainter(plane).setFunction(new ParamFunction()).setColor(Color.GREEN);
        handleFrameEvents();
        handleGraphicsPanelEvents();
        handlerControlPanelEvents();
        handleFunctionsPanelEvents();
        initializeAndAddPainters();
        repaint();
    }

    // установка layout  и упаковка компонент
    private void setupLayoutAndPack(){
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addComponent(functionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(functionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(5)
        );
        setLayout(gl);
        pack();
    }

    // обработка событий главной формы (JFrame)
    private void handleFrameEvents(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateCartScreenDimension(graphicsPanel.getWidth(), graphicsPanel.getHeight());
                graphicsPanel.repaint();
            }
        });
    }

    // обработка событий GraphicsPanel
    private void handleGraphicsPanelEvents(){
        graphicsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateCartScreenDimension(graphicsPanel.getWidth(), graphicsPanel.getHeight());
                graphicsPanel.repaint();
            }
        });
    }

    // обработка событий ControlPanel
    private void handlerControlPanelEvents(){
        controlPanel.addResizedListener(new ISpinnerListener() {
            @Override
            public void valueChanged() {
                var xMin = controlPanel.getXMin();
                var xMax = controlPanel.getXMax();
                var yMin = controlPanel.getYMin();
                var yMax = controlPanel.getYMax();
                updateCartScreenArgs(xMin, xMax, yMin, yMax);
                graphicsPanel.repaint();
            }
        });
    }

    private void handleFunctionsPanelEvents(){
        functionsPanel.explicitCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var state = functionsPanel.explicitCheck.isSelected();
                explicitPainter.setEnabled(state);
                graphicsPanel.repaint();
            }
        });

        functionsPanel.parametricCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var state = functionsPanel.parametricCheck.isSelected();
                parametricPainter.setEnabled(state);
                graphicsPanel.repaint();
            }
        });
    }

    // инициализация новой декартовой плоскости с аргументами:
    // xMin = -5, xMax = 5, yMin = -5, yMax = 5
    private CartScreenPlane getNewCartScreenPlane(int width, int height){
        return new CartScreenPlane(width, height,
                -5, 5, -5, 5);
    }

    // обновление значений размеров декартовой плоскости
    private void updateCartScreenDimension(int width, int height){
        plane.setRealWidth(width);
        plane.setRealHeight(height);
    }

    // обновление значений аргументов декартовой плоскости
    private void updateCartScreenArgs(double xMin, double xMax, double yMin, double yMax){
        plane.setXMin(xMin);
        plane.setXMax(xMax);
        plane.setYMin(yMin);
        plane.setYMax(yMax);
    }

    // инициализация художников и их последующее добавление в GraphicsPanel
    private void initializeAndAddPainters(){
        graphicsPanel.addPainter(
                new CartesianPainter(plane)
        );
        graphicsPanel.addPainter(
                explicitPainter
        );
        graphicsPanel.addPainter(
                parametricPainter
        );
    }
}