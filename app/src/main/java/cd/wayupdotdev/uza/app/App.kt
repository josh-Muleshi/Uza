package cd.wayupdotdev.uza.app

import android.app.Application
import android.content.Context
import android.content.Intent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){
    companion object {
        fun restart(context: Context) {

            context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addCategory(Intent.CATEGORY_HOME)
                context.startActivity(this)
            }
        }
    }
}