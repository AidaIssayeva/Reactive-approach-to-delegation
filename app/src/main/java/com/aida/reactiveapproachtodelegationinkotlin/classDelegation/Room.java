package com.aida.reactiveapproachtodelegationinkotlin.classDelegation;

public class Room implements WindowActionListener, DoorActionListener {
    private Window window = new Window();
    private Door door = new Door();

    @Override
    public void openWindow() {
        window.openWindow();
    }

    @Override
    public void closeWindow() {
        window.closeWindow();
    }

    @Override
    public void openDoor() {
        door.openDoor();
    }

    @Override
    public void closeDoor() {
        door.closeDoor();
    }

}
