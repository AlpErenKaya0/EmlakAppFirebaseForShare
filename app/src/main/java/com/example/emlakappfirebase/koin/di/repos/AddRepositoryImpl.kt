package com.example.emlakappfirebase.koin.di.repos

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class AddRepositoryImpl(
 private val firestore: FirebaseFirestore,
 private var firebaseAuth: FirebaseAuth,
 private var storage: FirebaseStorage
) : AddRepository {
 override suspend fun saveDataToFirestore(
  title: String,
  name: String,
  address: String,
  photos: List<Uri>,
  positiveTitle: String,
  negativeTitle: String,
  price: String,
  isPrivate: Boolean // Bu parametre artık yola etki etmeyecek
 ) {
  val userId = firebaseAuth.currentUser?.uid
  if (userId == null) {
   println("User is not authenticated")
   return
  }

  // Firestore'a kaydedilecek veri yapısı
  val data = hashMapOf(
   "title" to title,
   "name" to name,
   "address" to address,
   "userId" to userId, // UserId verisi kaydedilmeye devam edecek
   "photos" to mutableListOf<String>(), // Fotoğraf URL'lerini tutacak liste
   "negativeTitle" to negativeTitle,
   "positiveTitle" to positiveTitle,
   "price" to price,
   "isPrivate" to isPrivate // Veriyi kaydetmeye devam ediyoruz ama yolda kullanmıyoruz
  )

  // Post için benzersiz bir ID oluştur
  val postId = firestore.collection("posts").document().id

  // Artık userId ile üst konum oluşturulmadan kaydetme yapılacak
  val postPath = "posts/$postId"

  // Her fotoğrafı ayrı ayrı yükle
  withContext(Dispatchers.IO) {
   photos.forEachIndexed { index, uri ->
    val imageName = "photo_${UUID.randomUUID()}.jpg"
    val postFolder = "posts/$postId" // userId olmadan doğrudan posts altında depolanacak
    val imageReference = storage.reference.child(postFolder).child(imageName)

    // Fotoğrafı yükleme işlemi
    val uploadTask = imageReference.putFile(uri)
    uploadTask.addOnSuccessListener {
     imageReference.downloadUrl.addOnSuccessListener { downloadUrl ->
      val downloadURLString = downloadUrl.toString()
      // Fotoğrafın URL'sini listeye ekliyoruz
      (data["photos"] as MutableList<String>).add(downloadURLString)

      // Eğer son fotoğraf yüklendiyse veriyi Firestore'a kaydet
      if ((data["photos"] as MutableList<String>).size == photos.size) {
       firestore.document(postPath)
        .set(data)
        .addOnSuccessListener {
         println("Post saved with ID: $postId")
        }
        .addOnFailureListener { e ->
         println("Error saving post: $e")
        }
      }
     }.addOnFailureListener {
      println("Error getting download URL: ${it.localizedMessage}")
     }
    }.addOnFailureListener {
     println("Error uploading photo: ${it.localizedMessage}")
    }
   }
  }
 }
}

/*
override suspend fun saveDataToFirestore(title: String, name: String, address: String, photos: List<Uri>) {
 val data = hashMapOf(
  "title" to title,
  "name" to name,
  "address" to address,
  "photos" to photos
 )

 try {
  firestore.collection("posts").add(data).await()  // await() ile coroutine beklemesi sağlanır
 } catch (e: Exception) {
  println("Error adding document $e")
 }
}
}
 */
    /*

    val database: FirebaseFirestore,
    override fun getNotes(user: User?, result: (AddUIState<List<Note>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addNote(note: Note, result: (AddUIState<Pair<Note, String>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note, result: (AddUIState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note, result: (AddUIState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun uploadSingleFile(fileUri: Uri, onResult: (AddUIState<Uri>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun uploadMultipleFile(
        fileUri: List<Uri>,
        onResult: (UiState<List<Uri>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
    val storageReference: StorageReference
    ) : AddRepository {

        override fun getNotes(user: User?, result: (AddUIState<List<Note>>) -> Unit) {
            database.collection(FireStoreCollection.NOTE)
                .whereEqualTo(FireStoreDocumentField.USER_ID,user?.id)
                .orderBy(FireStoreDocumentField.DATE, Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    val notes = arrayListOf<Note>()
                    for (document in it) {
                        val note = document.toObject(Note::class.java)
                        notes.add(note)
                    }
                    result.invoke(
                        AddUIState.Success(notes)
                    )
                }
                .addOnFailureListener {
                    result.invoke(
                        AddUIState.Failure(
                            it.localizedMessage
                        )
                    )
                }
        }

        override fun addNote(note: Note, result: (AddUIState<Pair<Note,String>>) -> Unit) {
            val document = database.collection(FireStoreCollection.NOTE).document()
            note.id = document.id
            document
                .set(note)
                .addOnSuccessListener {
                    result.invoke(
                        AddUIState.Success(Pair(note,"Note has been created successfully"))
                    )
                }
                .addOnFailureListener {
                    result.invoke(
                        AddUIState.Failure(
                            it.localizedMessage
                        )
                    )
                }
        }

        override fun updateNote(note: Note, result: (AddUIState<String>) -> Unit) {
            val document = database.collection(FireStoreCollection.NOTE).document(note.id)
            document
                .set(note)
                .addOnSuccessListener {
                    result.invoke(
                        AddUIState.Success("Note has been update successfully")
                    )
                }
                .addOnFailureListener {
                    result.invoke(
                        AddUIState.Failure(
                            it.localizedMessage
                        )
                    )
                }
        }

        override fun deleteNote(note: Note, result: (AddUIState<String>) -> Unit) {
            database.collection(FireStoreCollection.NOTE).document(note.id)
                .delete()
                .addOnSuccessListener {
                    result.invoke(AddUIState.Success("Note successfully deleted!"))
                }
                .addOnFailureListener { e ->
                    result.invoke(AddUIState.Failure(e.message))
                }
        }

        override suspend fun uploadSingleFile(fileUri: Uri, onResult: (AddUIState<Uri>) -> Unit) {
            try {
                val uri: Uri = withContext(Dispatchers.IO) {
                    storageReference
                        .putFile(fileUri)
                        .await()
                        .storage
                        .downloadUrl
                        .await()
                }
                onResult.invoke(AddUIState.Success(uri))
            } catch (e: FirebaseException){
                onResult.invoke(AddUIState.Failure(e.message))
            }catch (e: Exception){
                onResult.invoke(AddUIState.Failure(e.message))
            }
        }

        override suspend fun uploadMultipleFile(
            fileUri: List<Uri>,
            onResult: (AddUIState<List<Uri>>) -> Unit
        ) {
            try {
                val uri: List<Uri> = withContext(Dispatchers.IO) {
                    fileUri.map { image ->
                        async {
                            storageReference.child(NOTE_IMAGES).child(image.lastPathSegment ?: "${System.currentTimeMillis()}")
                                .putFile(image)
                                .await()
                                .storage
                                .downloadUrl
                                .await()
                        }
                    }.awaitAll()
                }
                onResult.invoke(AddUIState.Success(uri))
            } catch (e: FirebaseException) {
                onResult.invoke(AddUIState.Failure(e.message))
            }catch (e: Exception){
                onResult.invoke(AddUIState.Failure(e.message))
            }
        }
    }
*/