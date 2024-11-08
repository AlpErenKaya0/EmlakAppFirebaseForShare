package com.example.emlakappfirebase.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.emlakappfirebase.ui.screens.navigation.NavigationItem
import com.google.firebase.auth.FirebaseAuth


class MainViewModel : ViewModel() {

    private val TAG = MainViewModel::class.simpleName

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = "settingsScreen"
        ),
        NavigationItem(
            title = "Favorite",
            icon = Icons.Default.Favorite,
            description = "Favorite Screen",
            itemId = "favoriteScreen"
        )
    )

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
              //  navController()
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }

//bundan önce ekran koyup isim soyisim yaş bilgisi çekilecek.
// Bu bilgiler Firestore Database'e kaydedilecek EMAİL ile birlikte ve
// sonrasında kullanıcının bilgisi önceki ekrandaki tercihlerinden gelecek.
// Profil Fotoğrafı da ekleyecek bir dairenin içine o da aynı şekilde eklenecek.
// Fotoğraf izni ekleme dairesine tıklanınca uygulama zaten izni almışsa direkt Galeriye gidecek daha önceden almadıysa
// EASYPERMİSSİON ile alınacak ve eklenecek.
// Fotoğraflar olacak ya ana ekranda LazyColumn İçinde evlerin çekilmiş fotoğrafları eklenen yorumlarla birlikte,
// onda da koordinatın adresleri kullanıcı tarafından eklenmek istenirse eklenecek.
// Eklenen adrese DynamicLink Mantığında ulaşılıp hemen ilgili konum haritada açılacak.

    //OLUMLU VE OLUMSUZ FOTOĞRAFLAR NASIL EKLENECEK EN ÖNEMLİ KISIM O DRAG MANTIĞINDA MI MESELA?

    val emailId: MutableLiveData<String> = MutableLiveData()

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }

}