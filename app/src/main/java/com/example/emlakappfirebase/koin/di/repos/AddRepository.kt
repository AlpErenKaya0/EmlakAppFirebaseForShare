package com.example.emlakappfirebase.koin.di.repos

import android.net.Uri

interface AddRepository {
    suspend fun saveDataToFirestore(title: String,
                                    name: String,
                                    address: String,
                                    photos: List<Uri>,
                                    positiveTitle:String,
                                    negativeTitle:String,
                                    price:String,
                                    isPrivate:Boolean)

}
/*
fun getNotes(user: User?, result: (UiState<List<Note>>) -> Unit)
    fun addNote(note: Note, result: (UiState<Pair<Note,String>>) -> Unit)
    fun updateNote(note: Note, result: (UiState<String>) -> Unit)
    fun deleteNote(note: Note, result: (UiState<String>) -> Unit)
    suspend fun uploadSingleFile(fileUri: Uri, onResult: (UiState<Uri>) -> Unit)
    suspend fun uploadMultipleFile(fileUri: List<Uri>, onResult: (UiState<List<Uri>>) -> Unit)

 */