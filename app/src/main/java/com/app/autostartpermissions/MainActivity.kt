package com.app.autostartpermissions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.autostartpermission.PermissionActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionActivity.start(this)
    }
}