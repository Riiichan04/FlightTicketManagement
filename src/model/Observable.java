package model;

import utilities.JDialogCreator;
import view.Observer;

public interface Observable {
    void addObserver(Observer ob);
    void removeObserver(Observer ob);
    void removeObserver(int i);
    void notifyObserver(JDialogCreator dialog);
}
