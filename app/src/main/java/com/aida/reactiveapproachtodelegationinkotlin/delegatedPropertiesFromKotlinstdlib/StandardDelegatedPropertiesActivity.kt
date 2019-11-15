package com.aida.reactiveapproachtodelegationinkotlin.delegatedPropertiesFromKotlinstdlib

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.aida.reactiveapproachtodelegationinkotlin.R
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class StandardDelegatedPropertiesActivity : AppCompatActivity() {

    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }

    private val buttonSubmit: Button by lazy {
        findViewById<Button>(R.id.buttonSubmit)
    }

    private val buttonDelete: Button by lazy {
        findViewById<Button>(R.id.buttonDelete)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.progressCircular)
    }

    private var count by Delegates.notNull<Int>()

    private var isLoading by Delegates.observable(false) { p, old, new ->
        buttonDelete.isEnabled = !new
        buttonSubmit.isEnabled = !new
        progressBar.isVisible = new

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_delegated_properties)

        val map = mapOf(
            "firstName" to "Aida",
            "middleName" to null,
            "lastName" to "Issayeva",
            "phoneNumber" to "212-212-2121",
            "income" to 1000000000.00
        )
        val person = Profile(map)

        buttonSubmit.setOnClickListener {
            Completable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(Completable.fromAction {
                    count = 1
                    isLoading = true
                })
                .subscribe {
                    isLoading = false
                    textView.text = person.firstName.plus(count)
                }
        }

    }

}

class Profile(map: Map<String, Any?>) {
    val firstName: String by map
    val lastName: String by map
    val phoneNumber: String by map
    val income: Double by map
}