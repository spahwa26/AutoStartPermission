package com.app.autostartpermissions

import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import java.util.*


class PermissionActivity : AppCompatActivity() {
    private var permissionsIntents: MutableList<Intent> = Arrays.asList(

        Intent().setComponent(
            ComponentName(
                "com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.process.ProtectActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.coloros.safecenter",
                "com.coloros.safecenter.permission.startup.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.coloros.safecenter",
                "com.coloros.safecenter.startupapp.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.oppo.safe",
                "com.oppo.safe.permission.startup.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.iqoo.secure",
                "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.iqoo.secure",
                "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.asus.mobilemanager",
                "com.asus.mobilemanager.entry.FunctionActivity"
            )
        ).setData(
            Uri.parse("mobilemanager://function/entry/AutoStart")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startPermissionIntent(this)
    }

    private fun startPermissionIntent(context: Context) {
        val settings = context.getSharedPreferences("ProtectedApps", MODE_PRIVATE)
        val skipMessage = settings.getBoolean("skipProtectedAppCheck", false)
        if (!skipMessage) {
            val editor = settings.edit()
            var foundCorrectIntent = false
            for (intent in permissionsIntents) {
                if (isCallable(context, intent)) {
                    foundCorrectIntent = true
                    val dontShowAgain = AppCompatCheckBox(context)
                    dontShowAgain.text = "Do not show again"
                    dontShowAgain.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
                        editor.putBoolean("skipProtectedAppCheck", isChecked)
                        editor.apply()
                    }
                    val finalFoundCorrectIntent = foundCorrectIntent
                    AlertDialog.Builder(context)
                        .setTitle(Build.MANUFACTURER + " Protected Apps")
                        .setMessage(
                            String.format(
                                "%s requires to be enabled in 'Protected Apps' to function properly.%n",
                                context.getString(R.string.app_name)
                            )
                        ) //.setView(dontShowAgain)
                        .setCancelable(false)
                        .setPositiveButton("Go to settings") { dialog: DialogInterface?, which: Int ->
                            if (finalFoundCorrectIntent) {
                                editor.putBoolean("skipProtectedAppCheck", true)
                                editor.apply()
                            }
                            try {
                                if (Build.MANUFACTURER.toLowerCase() == "oppo") requestAutoStartPermission() else context.startActivity(
                                    intent
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            finish()
                        }
                        .setNegativeButton(
                            android.R.string.cancel
                        ) { dialog, which -> finish() }
                        .show()
                    break
                }
            }
            if (!foundCorrectIntent) finish()
        } else finish()
    }

    private fun requestAutoStartPermission() {
        //com.coloros.safecenter.permission.singlepage.PermissionSinglePageActivity     listpermissions
        //com.coloros.privacypermissionsentry.PermissionTopActivity                     privacypermissions
        // getPackageManager().getLaunchIntentForPackage("com.coloros.safecenter");
        if (Build.MANUFACTURER == "OPPO") {
            try {
                startActivity(
                    Intent().setComponent(
                        ComponentName(
                            "com.coloros.safecenter",
                            "com.coloros.safecenter.permission.startup.FakeActivity"
                        )
                    )
                )
            } catch (e: Exception) {
                try {
                    startActivity(
                        Intent().setComponent(
                            ComponentName(
                                "com.coloros.safecenter",
                                "com.coloros.safecenter.permission.startupapp.StartupAppListActivity"
                            )
                        )
                    )
                } catch (e1: Exception) {
                    try {
                        startActivity(
                            Intent().setComponent(
                                ComponentName(
                                    "com.coloros.safecenter",
                                    "com.coloros.safecenter.permission.startupmanager.StartupAppListActivity"
                                )
                            )
                        )
                    } catch (e2: Exception) {
                        try {
                            startActivity(
                                Intent().setComponent(
                                    ComponentName(
                                        "com.coloros.safe",
                                        "com.coloros.safe.permission.startup.StartupAppListActivity"
                                    )
                                )
                            )
                        } catch (e3: Exception) {
                            try {
                                startActivity(
                                    Intent().setComponent(
                                        ComponentName(
                                            "com.coloros.safe",
                                            "com.coloros.safe.permission.startupapp.StartupAppListActivity"
                                        )
                                    )
                                )
                            } catch (e4: Exception) {
                                try {
                                    startActivity(
                                        Intent().setComponent(
                                            ComponentName(
                                                "com.coloros.safe",
                                                "com.coloros.safe.permission.startupmanager.StartupAppListActivity"
                                            )
                                        )
                                    )
                                } catch (e5: Exception) {
                                    try {
                                        startActivity(
                                            Intent().setComponent(
                                                ComponentName(
                                                    "com.coloros.safecenter",
                                                    "com.coloros.safecenter.permission.startsettings"
                                                )
                                            )
                                        )
                                    } catch (e6: Exception) {
                                        try {
                                            startActivity(
                                                Intent().setComponent(
                                                    ComponentName(
                                                        "com.coloros.safecenter",
                                                        "com.coloros.safecenter.permission.startupapp.startupmanager"
                                                    )
                                                )
                                            )
                                        } catch (e7: Exception) {
                                            try {
                                                startActivity(
                                                    Intent().setComponent(
                                                        ComponentName(
                                                            "com.coloros.safecenter",
                                                            "com.coloros.safecenter.permission.startupmanager.startupActivity"
                                                        )
                                                    )
                                                )
                                            } catch (e8: Exception) {
                                                try {
                                                    startActivity(
                                                        Intent().setComponent(
                                                            ComponentName(
                                                                "com.coloros.safecenter",
                                                                "com.coloros.safecenter.permission.startup.startupapp.startupmanager"
                                                            )
                                                        )
                                                    )
                                                } catch (e9: Exception) {
                                                    try {
                                                        startActivity(
                                                            Intent().setComponent(
                                                                ComponentName(
                                                                    "com.coloros.safecenter",
                                                                    "com.coloros.privacypermissionsentry.PermissionTopActivity.Startupmanager"
                                                                )
                                                            )
                                                        )
                                                    } catch (e10: Exception) {
                                                        try {
                                                            startActivity(
                                                                Intent().setComponent(
                                                                    ComponentName(
                                                                        "com.coloros.safecenter",
                                                                        "com.coloros.privacypermissionsentry.PermissionTopActivity"
                                                                    )
                                                                )
                                                            )
                                                        } catch (e11: Exception) {
                                                            try {
                                                                startActivity(
                                                                    Intent().setComponent(
                                                                        ComponentName(
                                                                            "com.coloros.safecenter",
                                                                            "com.coloros.safecenter.FakeActivity"
                                                                        )
                                                                    )
                                                                )
                                                            } catch (e12: Exception) {
                                                                e12.printStackTrace()
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isCallable(context: Context, intent: Intent): Boolean {
        val list = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return list.size > 0
    }
}