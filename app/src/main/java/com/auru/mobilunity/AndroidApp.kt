package com.auru.mobilunity

import android.app.Application
import com.auru.mobilunity.injection.AppComponent
import com.auru.mobilunity.injection.AppModule
import com.auru.mobilunity.injection.DaggerAppComponent
import com.auru.mobilunity.injection.DataModule

class AndroidApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
//            .authModule(AuthModule(BuildConfig.BASE_URL))
            .dataModule(DataModule(baseUrl))
            .build()
    }

//    val attachmentLoaderFactory: AttachmentLoaderFactory by lazy { component.attachmentLoaderFactory() }
//    val workerFactory: WorkerFactory by lazy { component.workerFactory() }

    override fun onCreate() {
        super.onCreate()

//        when (BuildConfig.FLAVOR_api) {
//            "production" -> initErrorReporting()
//            "development" -> initDebugConfig()
//            else -> if(BuildConfig.DEBUG){
//                Stetho.initializeWithDefaults(this)
//            }
//        }

        // Components initialization
//        initComponents()

    }

//    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }
//
//    private fun initDebugConfig() {
//        Timber.plant(Timber.DebugTree())
//        Timber.plant(FileLoggingTree(this, AndroidUtils.getProcessName(this)))
//
//        // Branch logging for debugging
//        Branch.enableDebugMode()
//        Stetho.initializeWithDefaults(this)
//    }
//
//    private fun initErrorReporting() {
//        val anrWatchDog = ANRWatchDog(8000)
//        anrWatchDog.setANRListener { error ->
//            Crashlytics.logException(error)
//        }
//        anrWatchDog.start()
//    }
//
//    private fun initComponents() {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
//        AndroidUtils.init(this)
//        JodaTimeAndroid.init(this)
//        RxPaparazzo.register(this)
//        RxJavaPlugins.setErrorHandler { Timber.w(it) }
//        WorkManager.initialize(
//            this,
//            androidx.work.Configuration.Builder().setWorkerFactory(workerFactory).build()
//        )
//    }
}
