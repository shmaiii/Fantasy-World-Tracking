package ui;

import model.Event;
import model.EventLog;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        WorldApp myApp = new WorldApp();
        myApp.displayHomePage();

        for (Event ev : EventLog.getInstance()) {
            System.out.println(ev.toString());
        }
    }
}
