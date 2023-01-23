package function;


// Класс реализующий интерфейс ParametricFunction, т.е. является прямой реализацией некоторой функции, заданной параметрически
public class ParamFunction implements ParametricFunction {

    @Override
    public double getX(double t) {
        return (t * t - 1) / (t * (t + 2));
    }

    @Override
    public double getY(double t) {
        return t * t / ((t + 2) * (t + 1));
    }
}