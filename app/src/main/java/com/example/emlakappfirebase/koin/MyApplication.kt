package com.example.emlakappfirebase.koin
import android.app.Application
import com.example.emlakappfirebase.koin.di.addModule
import com.example.emlakappfirebase.koin.di.exploreModule
import com.example.emlakappfirebase.koin.di.favouriteModule
import com.example.emlakappfirebase.koin.di.loginModule
import com.example.emlakappfirebase.koin.di.myBuildingModule
import com.example.emlakappfirebase.koin.di.signupModule
//import com.example.emlakappfirebase.koin.di.anotherModule
//import com.example.emlakappfirebase.koin.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(addModule,
                loginModule, myBuildingModule, signupModule, exploreModule, favouriteModule,
                /*databaseModule*/ /*sessionModule*/ )
        }
    }
}
//Telefon numarası üzerinden invite atılabilir,
// sadece o kişilerin göreceği şekilde filtreleme yapılabilir.
