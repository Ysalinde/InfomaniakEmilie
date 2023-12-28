package com.example.infomaniakemilie.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/*
 * This function allows me to check the status of the internet on the Smarphone
 * using Connectivity_Service given by the Android Context.
 *
 * This is the smallest test I can do before sending Api Request
 */
fun isConnected(context: Context): Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return when {
        /*
         * The access of the connectivity status changed on Android 6
         * You can check the SDK INT vs VERSION CODE : https://apilevels.com/
         */
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val cap = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            when {
                cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        // If you want to check on the phone who got Android 5 or less
        else -> {
            val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
            return when( activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_VPN -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }
        }
    }
}