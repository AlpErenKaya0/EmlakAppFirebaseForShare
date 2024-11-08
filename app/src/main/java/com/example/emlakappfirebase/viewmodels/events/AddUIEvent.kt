package com.example.emlakappfirebase.viewmodels.events

import android.net.Uri

sealed class AddUIEvent {
    //TOPLU PHOTO URİ İÇİN LİST
    data class PhotosUri(val photosUri: Uri):AddUIEvent()
    data class NameChanged(val name:String): AddUIEvent()
    data class TitleChanged(val title: String) : AddUIEvent()
    data class AddressChanged(val address: String):AddUIEvent()
    data class LocationChanged(val location:String):AddUIEvent()
    data class PositiveTitleChanged(val positiveTitle:String):AddUIEvent()
    data class NegativeTitleChanged(val negativeTitle:String):AddUIEvent()
    data class PriceChanged(val price:String):AddUIEvent()
    data class PrivateChecked(val isPrivate:Boolean):AddUIEvent()
    object SaveButtonClicked : AddUIEvent()
}
//Burası düzeltilecek
