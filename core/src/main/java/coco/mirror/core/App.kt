package coco.mirror.core

import android.app.Application
import android.content.Context
import me.weishu.reflection.Reflection
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            AndroidLogger(Level.DEBUG)
            modules(appModule)
            androidContext(this@App)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Reflection.unseal(base);
    }

    private val appModule = module {
        single { Env() }
    }

}