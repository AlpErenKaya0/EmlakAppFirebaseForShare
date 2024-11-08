package com.example.emlakappfirebase.viewmodels.states

import android.net.Uri

data class AddUIState(
    //TOPLU PHOTO URİ İÇİN LİST
    var photosUri:Uri?=null ,
    var name    :String = "",
    var title  :String = "",
    var address  :String = "",
    var location  :String = "",
    var positiveTitle  :String = "",
    var negativeTitle  :String = "",
    //price'ı string mi yoksa int mi yapmak daha mantıklı bilmiyorum
    var price: String = "" ,
    var isPrivate :Boolean = false,

    var nameError :Boolean = false,
    var titleError : Boolean = false,
    var addressError :Boolean = false,
    var locationError : Boolean = false,
    var positiveTitleError :Boolean = false,
    var negativeTitleError : Boolean = false,
    var priceError :Boolean = false,
)
//Burası Düzeltilecek
