package com.aida.reactiveapproachtodelegationinkotlin.propertyDelegation

import kotlin.reflect.KProperty


class UserNameDelegate {

    operator fun getValue(thisRef: User?, property: KProperty<*>): String {
        return "${thisRef?.firstName}_ ${thisRef?.lastName}"

    }

    operator fun setValue(thisRef: User?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}