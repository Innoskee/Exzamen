package gui.panels;

import gui.painters.Painter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Панель, содержащий в себе рисунки добавленных художников
 * */
public class GraphicsPanel extends JPanel {
    // список рисовальщиков
    private final ArrayList<Painter> painters = new ArrayList<>();

    public GraphicsPanel(){
        setBorder(new EtchedBorder());
        setBackground(Color.WHITE);
    }

    /**
     * Добавление художника
     * @param p добавляемый рисовальщик
     * */
    public void addPainter(Painter p){
        painters.add(p);
    }
    /**
     * Удаление рисовальщика
     * @param p удаляемый рисовальщик
     * */
    public void removePainter(Painter p){
        painters.remove(p);
    }

    /**
     * Переопределенный метод класса {@link JPanel}, вызывающийся при необходимости перерисовать панель
     * */
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics); // вызов этого же метода у родительского класса
        painters.forEach(painter -> painter.paint(graphics)); // вызов метода paint у всех добавленных рисовальщиков
    }
}