package com.example.emlakappfirebase.ui.screens.logic

import android.net.Uri

data class HouseData(
    val title: String = "",
    val name: String = "",
    val address: String = "",
    val userId: String = "",
    val photos: List<String> = emptyList(),
    val positiveTitle: String = "",
    val negativeTitle: String = "",
    val price: String = "",
    val isPrivate: Boolean = false
) {
    // Parametresiz constructor gerekli yoksa firebase desirealizable işlem yapamıyor
    constructor() : this("", "", "", "", emptyList(), "", "", "", false)
}
