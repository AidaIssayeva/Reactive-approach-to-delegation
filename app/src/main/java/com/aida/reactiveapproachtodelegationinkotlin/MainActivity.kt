package com.aida.reactiveapproachtodelegationinkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.aida.reactiveapproachtodelegationinkotlin.customView.ButtonState
import com.aida.reactiveapproachtodelegationinkotlin.delegatedPropertiesFromKotlinstdlib.StandardDelegatedPropertiesActivity
import com.aida.reactiveapproachtodelegationinkotlin.propertyDelegation.CustomDelegatedPropertyActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, StandardDelegatedPropertiesActivity::class.java))
        }

        button.state = ButtonState.ShowLiked

        disposable.add(RxView.clicks(button)
            .subscribe {
                when (button.state) {
                    ButtonState.SendComment -> sendComment()
                    ButtonState.ShowLiked -> unlikePhoto()
                    ButtonState.ShowUnLiked -> likePhoto()
                }
            })

        disposable.add(RxTextView.textChanges(editText)
            .subscribe {
                button.state = when (it.count()) {
                    0 -> ButtonState.ShowLiked
                    else -> ButtonState.SendComment
                }
            })

    }

    private fun sendComment() {
        //api call
        Log.v(javaClass.name, "sendComment")
    }

    private fun likePhoto() {
        //api call
        Log.v(javaClass.name, "likePhoto")
    }

    private fun unlikePhoto() {
        //api call
        Log.v(javaClass.name, "unlikePhoto")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
