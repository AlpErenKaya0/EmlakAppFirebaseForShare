package com.example.emlakappfirebase.viewmodels.states

import android.net.Uri

data class ExploreUIState(
    var name: String = "",
    var address: String = "",
    var photos: List<Uri> = listOf(),
    var title: String = "",

    var nameError: Boolean = false,
    var addressError: Boolean = false,
    var titleError: Boolean = false
)


