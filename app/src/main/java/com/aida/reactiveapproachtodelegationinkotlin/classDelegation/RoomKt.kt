package com.aida.reactiveapproachtodelegationinkotlin.classDelegation


class RoomKt : WindowActionListener by Window(), DoorActionListener by Door() {
    //write down main functionality of the room
}