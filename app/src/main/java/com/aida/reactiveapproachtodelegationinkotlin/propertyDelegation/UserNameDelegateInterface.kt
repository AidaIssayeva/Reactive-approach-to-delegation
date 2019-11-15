package com.aida.reactiveapproachtodelegationinkotlin.propertyDelegation

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class UserNameDelegateInterface : ReadOnlyProperty<User, String> {
    override fun getValue(thisRef: User, property: KProperty<*>): String {
        return "${thisRef.firstName}_ ${thisRef.lastName}"
    }

}