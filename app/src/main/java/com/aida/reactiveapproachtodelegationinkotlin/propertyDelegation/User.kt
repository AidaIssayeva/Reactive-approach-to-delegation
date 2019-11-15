package com.aida.reactiveapproachtodelegationinkotlin.propertyDelegation


class User(val firstName: String, val lastName: String) {
    var userName: String by UserNameDelegate()
}