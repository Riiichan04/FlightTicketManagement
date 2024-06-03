package controller;

import model.ListAccount;
import model.ListFlight;
import model.MainSystem;
import model.Model;
import view.SignInFrame;
import view.View;

public class Controller implements IController{
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public static void main(String[] args) throws Exception {
        ListAccount la = new ListAccount();
        la.loadAccount();
        ListFlight lf = new ListFlight();
        lf.loadFlight();
        MainSystem ms = MainSystem.getInstance();
        ms.setListAccount(la);
        Model model = new Model(ms);
        SignInFrame view = new SignInFrame(model);
    }
}
