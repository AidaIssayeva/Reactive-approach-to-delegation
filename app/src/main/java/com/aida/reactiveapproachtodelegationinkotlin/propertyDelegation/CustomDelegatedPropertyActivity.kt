package com.aida.reactiveapproachtodelegationinkotlin.propertyDelegation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aida.reactiveapproachtodelegationinkotlin.R

class CustomDelegatedPropertyActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_delegated_property)

        val user = User("Aida", "Issayeva")
        println("username:" + user.userName)
    }
}
