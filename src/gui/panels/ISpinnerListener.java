
package gui.panels;

/**
 * Интерфейс, характеризующий слушателя событий на спиннерах объекта класса ControlPanel
 * */
public interface ISpinnerListener extends IListener{ // данный интерфейс является наследником интерфейса IListener, сделано это для удобного управления слушателями разных событий
    public void valueChanged(); // обработка события
}