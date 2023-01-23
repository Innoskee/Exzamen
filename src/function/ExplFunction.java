package function;

// Класс реализующий интерфейс ExplicitFunction, т.е. является прямой реализацией некоторой функции, заданной параметрически
public class ExplFunction implements ExplicitFunction {
    @Override
    public double getY(double x) {
        return 3 * x - x * x;
    }
}