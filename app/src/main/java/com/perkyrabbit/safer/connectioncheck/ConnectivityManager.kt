package com.perkyrabbit.safer.connectioncheck

import android.app.Application

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager
@Inject
constructor(
  application: Application,
) {
  private val connectionLiveData = ConnectionLiveData(application)



  var _isNetworkAvailable = MutableLiveData<Boolean>()
  val isNetworkAvailable: LiveData<Boolean> = _isNetworkAvailable

  fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.observe(lifecycleOwner) { isConnected ->
      isConnected?.let { _isNetworkAvailable.postValue(isConnected) }
    }
  }

  fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.removeObservers(lifecycleOwner)
  }
}