package gui.painters;

import gui.convertion.CartScreenPlane;

import java.awt.*;

/**
 * Класс родительского рисовальщика
 * */
public abstract class Painter {
    // модификатор доступа protected для прямого доступа к полю производным классам
    protected CartScreenPlane _plane;

    public Painter(CartScreenPlane plane){
        _plane = plane;
    }

    /**
     * Рисование относительно заданного графического контекста
     * @param graphics графический контекст
     * */
    public abstract void paint(Graphics graphics);
}