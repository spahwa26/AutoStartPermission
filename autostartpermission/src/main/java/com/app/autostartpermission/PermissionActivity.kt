package com.app.autostartpermission

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        val title =
            if (alertTitle.isNullOrBlank()) Build.MANUFACTURER + " Protected Apps" else alertTitle
        val message =
            if (message.isNullOrBlank()) String.format(
                "%s requires to be enabled in 'Protected Apps' to function properly.%n",
                context.getString(R.string.app_name)
            ) else message
        val positiveButton =
            if (positiveButton.isNullOrBlank()) "Go to settings" else positiveButton
        val negativeButton =
            if (negativeButton.isNullOrBlank()) context.getString(android.R.string.cancel) else negativeButton
        val doneButton =
            if (doneButton.isNullOrBlank()) "Done" else doneButton
        if (!skipMessage) {
            val editor = settings.edit()
            var foundCorrectIntent = false
            for (intent in permissionsIntents) {
                if (isCallable(context, intent)) {
                    foundCorrectIntent = true
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setNeutralButton(positiveButton, null)
                        .setNegativeButton(negativeButton) { _, _ ->
                            finish()
                        }
                    alertBuilder.setPositiveButton(doneButton) { _, _ ->
                        editor.putBoolean("skipProtectedAppCheck", true)
                        editor.apply()
                        finish()
                    }
                    val dialog = alertBuilder.create()
                    dialog.setOnShowListener {
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener {
                            try {
                                if (Build.MANUFACTURER.toLowerCase(Locale.ROOT) == "oppo") requestAutoStartPermission() else context.startActivity(
                                    intent
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                    dialog.show()
                    break
                }
            }
            if (!foundCorrectIntent) finish()
        } else finish()
    }

    private fun requestAutoStartPermission() {
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

    @SuppressLint("QueryPermissionsNeeded")
    private fun isCallable(context: Context, intent: Intent): Boolean {
        val list = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return list.size > 0
    }

    companion object {
        private var alertTitle: String? = null
        private var message: String? = null
        private var positiveButton: String? = null
        private var negativeButton: String? = null
        private var doneButton: String? = null
        fun start(
            context: Context,
            title: String?=null,
            message: String?=null,
            negativeButton: String?=null,
            positiveButton: String?=null,
            doneButton: String?=null
        ) {
            this.alertTitle = title
            this.message = message
            this.positiveButton = positiveButton
            this.negativeButton = negativeButton
            this.doneButton = doneButton
            context.startActivity(Intent(context, PermissionActivity::class.java))
        }
    }

}