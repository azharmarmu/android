package com.example.sample_tuya

import android.app.Application
import com.tuya.smart.home.sdk.TuyaHomeSdk
import com.tuya.smart.sdk.TuyaSdk
import io.flutter.app.FlutterApplication

class DemoApp: FlutterApplication() {

    override fun onCreate() {
        super.onCreate()
        TuyaSdk.init(this, "xhs3dh5txqkkhrg3duum", "tata7hd4edw43qykg4gdtfmgp85dg4j3")
    }
}