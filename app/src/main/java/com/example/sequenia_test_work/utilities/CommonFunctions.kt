package com.example.sequenia_test_work.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

object CommonFunctions {
    fun showToast(context: Context, text: String, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, text, duration).show()

    fun showToast(context: Context, text: Int, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, text, duration).show()

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.let {
            val capabilities =
                it.getNetworkCapabilities(connectivityManager.activeNetwork)//TODO resolve

            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        }
        return false
    }
}