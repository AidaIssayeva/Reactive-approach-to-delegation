package com.aida.reactiveapproachtodelegationinkotlin.classDelegation;

/**
 * Living room without door
 */
public class LivingRoom implements WindowActionListener {
    private Window window = new Window();
    @Override
    public void openWindow() {
        window.openWindow();
    }

    @Override
    public void closeWindow() {
        window.closeWindow();
    }
}
