package com.perkyrabbit.safer.connectioncheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityManager.isNetworkAvailable.observe(this){
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }
}