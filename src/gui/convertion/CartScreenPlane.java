package gui.convertion;

/**
 * Контекст декартовой плоскости
 * */
public class CartScreenPlane {

    // размеры декартовой плоскости
    private int realWidth;
    private int realHeight;

    // аргументы декартовой плоскости
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    /**
      realWidth ширина экрана
      realHeight высота экрана
     * */
    public CartScreenPlane(int realWidth, int realHeight,
                        double xMin, double xMax, double yMin, double yMax) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    // Сеттеры
    public void setXMin(double xMin){
        this.xMin = xMin;
    }
    public void setXMax(double xMax){
        this.xMax = xMax;
    }
    public void setYMin(double yMin){
        this.yMin = yMin;
    }
    public void setYMax(double yMax){
        this.yMax = yMax;
    }
    public void setRealWidth(int RealWidth){
        this.realWidth = Math.abs(RealWidth);
    }
    public void setRealHeight(int RealHeight){
        this.realHeight = Math.abs(RealHeight);
    }

    // Геттеры
    public int getWidth() { return realWidth - 1; }
    public int getHeight() {
        return realHeight - 1;
    }
    public double getXMin() {
        return xMin;
    }
    public double getXMax() {
        return xMax;
    }
    public double getYMin() {
        return yMin;
    }
    public double getYMax() {
        return yMax;
    }

    /**
     * Отношение ширины экрана к длине обозримой оси X в текущем контексте декартовой плоскости
     * */
    public double getXDen() {
        return getWidth() / (xMax - xMin);
    }

    /**
     * Отношение высоты экрана к длине обозримой оси Y в текущем контексте декартовой плоскости
     * */
    public double getYDen() {
        return getHeight() / (yMax - yMin);
    }
}