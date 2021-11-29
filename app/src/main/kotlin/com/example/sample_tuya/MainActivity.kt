package com.example.sample_tuya

import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.tuya.smart.android.user.api.ILoginCallback
import com.tuya.smart.android.user.bean.User
import com.tuya.smart.home.sdk.TuyaHomeSdk

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            // Note: this method is invoked on the main thread.
            // TODO
            if (call.method.equals("getPlatformVersion")) {
                result.success("Android " + android.os.Build.VERSION.RELEASE)
            } else if (call.method == "loginWithEmail") {
                val email = "goghul@mariatech.io" //call.argument<String>("email")
                val password = "Brinks1234" //call.argument<String>("passwd")
                TuyaHomeSdk.getUserInstance().loginWithEmail("91",
                    email,
                    password,
                    object : ILoginCallback {
                        override fun onSuccess(user: User?) {
                            Log.d(
                                "loggedinuser",
                                "mobile : ${user?.mobile} phonecode :${user?.phoneCode} sID: ${user?.sid}"
                            )
                            result.success("Login succeeded, username:$user")
                        }

                        override fun onError(code: String?, error: String?) {
                            Log.d("loggedinuser", "code:" + code + "error:" + error)
                            result.error("code:" + code + "error:" + error, "", null)
                        }
                    })
            } else {
                result.notImplemented()
            }
        }
    }


    companion object {
        const val CHANNEL = "tuyasdk"
    }

}
