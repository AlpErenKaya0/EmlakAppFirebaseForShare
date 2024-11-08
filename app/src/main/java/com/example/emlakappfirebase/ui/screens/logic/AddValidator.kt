package com.example.emlakappfirebase.ui.screens.logic

object AddValidator {

    fun validateName(name: String): AddValidationResult {
        return AddValidationResult(
            (!name.isNullOrEmpty() && name.length >= 2) // Başlık en az 2 karakter olmalı
        )
    }

    fun validateTitle(title: String): AddValidationResult {
        return AddValidationResult(
            (!title.isNullOrEmpty() && title.length >= 2) // Başlık en az 2 karakter olmalı
        )
    }

    fun validateAddress(address: String): AddValidationResult {
        return AddValidationResult(
            (!address.isNullOrEmpty() && address.length >= 5) // Adres en az 5 karakter olmalı
        )
    }

}

// ValidationResult sınıfı
data class AddValidationResult(
    val status: Boolean = false
)