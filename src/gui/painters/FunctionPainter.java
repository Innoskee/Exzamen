package gui.painters;

import function.ExplicitFunction;
import function.Function;
import function.ParametricFunction;
import gui.convertion.CartScreenPlane;
import gui.convertion.Converter;

import java.awt.*;

/**
 * Рисовальщик функции
 * */
public class FunctionPainter extends Painter{
    private boolean _isEnabled = true;
    private Color _lineColor = Color.RED;

    private Function _targetFunction; // функция, которую необходимо нарисовать

    public FunctionPainter(CartScreenPlane plane) {
        super(plane);
    }

    /**
     * Установка функции, которую необходимо нарисовать
     * */
    public FunctionPainter setFunction(Function function){
        _targetFunction = function;
        return this;
    }

    public void setEnabled(boolean state){
        _isEnabled = state;
    }

    public FunctionPainter setColor(Color c){
        _lineColor = c;
        return this;
    }

    @Override
    public void paint(Graphics graphics) {
        if(_isEnabled){
            var step = 0.001; // шаг сдвига точки
            drawTargetFunction(graphics, step); // рисование функции
        }
    }

    // рисование функции
    private void drawTargetFunction(Graphics graphics, double step){
        var chartColor = _lineColor; // цвет линии графика
        if(graphics == null){
            return;
        }
        if(_targetFunction == null){ // если нечего рисовать, то ничего рисовать и не будем
            return;
        }
        graphics.setColor(chartColor);
        if(_targetFunction instanceof ExplicitFunction){ // проверка является тип функции _targetFunction типом ExplicitFunction (явной функции)
            drawExplicitFunction((ExplicitFunction) _targetFunction, graphics, step); // используем метод рисования явной функции
        }
        else if(_targetFunction instanceof ParametricFunction){ // проверка является тип функции _targetFunction типом ParametricFunction (параметрической функции)
            drawParametricFunction((ParametricFunction) _targetFunction, graphics, step); // используем метод рисования параметрической функции
        }
    }

    // рисование явной функции
    private void drawExplicitFunction(ExplicitFunction function, Graphics graphics, double step){
        var x = _plane.getXMin(); // точка x, которая будет двигаться по оси X с шагом step
        var xMax = _plane.getXMax(); // максимальное значение точки x
        while(x < xMax){
            var currentY = function.getY(x); // значение функции в точке x
            var nextY = function.getY(x + step); // значение функции в точке x + step
            if(isFinite(currentY) && isFinite(nextY)){ // проверка значений функций на бесконечность
                graphics.drawLine(
                        Converter.xCrt2Scr(x, _plane), Converter.yCrt2Scr(currentY, _plane),
                        Converter.xCrt2Scr(x + step, _plane), Converter.yCrt2Scr(nextY, _plane)
                ); // рисование отрезка между значениями функции в точках x и x + step
            }
            x += step; // двигаем точку дальше
        }
    }

    // рисование параметрической функции
    private void drawParametricFunction(ParametricFunction function, Graphics graphics, double step){
        // Пусть t лежит на отрезке [0; 10], т.к. в условиях задания нет никаких уточнений по этому поводу
        double t = 0.0; // точка t, которая будет двигаться по отрезку [0; 10] с шагом step
        double maxT = 10.0; // максимальное значение точки t
        while(t < maxT){
            var currX = function.getX(t); // значение функции X в точке t
            var currY = function.getY(t); // значение функции Y в точке t
            var nextX = function.getX(t + step); // значение функции X в точке t + step
            var nextY = function.getY(t + step); // значение функции Y в точке t + step
            if (isFinite(currX) && isFinite(nextX) && isFinite(currY) && isFinite(nextY)){ // проверка значений функций на бесконечность
                graphics.drawLine(
                        Converter.xCrt2Scr(currX, _plane), Converter.yCrt2Scr(currY, _plane),
                        Converter.xCrt2Scr(nextX, _plane), Converter.yCrt2Scr(nextY, _plane)
                );// рисование отрезка между значениями функций X и Y в точках t и t + step
            }
            t += step;
        }
    }

    // проверка числа на конечность для корректного рисования графиков
    private boolean isFinite(double number){
        double infinity = 90000000000000.; // искусственная бесконечность
        return Math.abs(number) < infinity;
    }
}