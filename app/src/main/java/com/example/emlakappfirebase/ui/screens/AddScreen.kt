package com.example.emlakappfirebase.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emlakappfirebase.R
import com.example.emlakappfirebase.utils.Appbar
import com.example.emlakappfirebase.utils.ImageCard
import com.example.emlakappfirebase.utils.IsPrivateChecked
import com.example.emlakappfirebase.utils.MyTextFieldComponent
import com.example.emlakappfirebase.utils.MyTextFieldComponentAddress
import com.example.emlakappfirebase.utils.MyTextFieldComponentLocation
import com.example.emlakappfirebase.utils.MyTextFieldComponentName
import com.example.emlakappfirebase.utils.MyTextFieldComponentNegative
import com.example.emlakappfirebase.utils.MyTextFieldComponentPositive
import com.example.emlakappfirebase.utils.MyTextFieldComponentTitle
import com.example.emlakappfirebase.utils.PriceComponent
import com.example.emlakappfirebase.viewmodels.AddViewModel
import com.example.emlakappfirebase.viewmodels.events.AddUIEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

//Koin'i ekliycez
@Composable
fun AddScreen(addViewModel: AddViewModel = koinViewModel(),navController: NavController,
              modifier: Modifier = Modifier) {
    /* Multiple foto için
LazyVerticalGrid(columns = ) {

}
     */
    val context = LocalContext.current // Context to show Toast


    //bu 2 alttaki kod ÖNEMLİ
    var selectedLatitude by remember { mutableStateOf<Double?>(null) }
    var selectedLongitude by remember { mutableStateOf<Double?>(null) }


    var showSuccessDialog by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var enableConfirmButton by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf(false) }


    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedImageUris = uris }
    )

    //Buraya bakacağız
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        // Button Row
        item {
            Appbar(toolbarTitle = "AddScreen", backButtonClicked = {
                showAlertDialog = true
//                navController.navigate("main_screen") {
//                    navController.navigateUp()
//                }
            }, saveButtonClicked = {
                if (addViewModel.isButtonEnable()) {
                    addViewModel.saveDataToFirestore(
                        title = addViewModel.addUIState.value.title,
                        name = addViewModel.addUIState.value.name,
                        address = addViewModel.addUIState.value.address,
                        photos = selectedImageUris,
                        positiveTitle = addViewModel.addUIState.value.positiveTitle,
                        negativeTitle = addViewModel.addUIState.value.negativeTitle,
                        price = addViewModel.addUIState.value.price,
                        isPrivate = addViewModel.addUIState.value.isPrivate

                    )
                    showSuccessDialog = true
                    enableConfirmButton = false
//                    navController.navigate("dummy_screen"){
//                        navController.navigateUp()
//
//                    }

                } else {
                    toastMessage = true
                }
            })

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Pick one photo")
                }
                Button(onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Pick multiple photo")
                }
            }
        }

        // Fotoğraf varsa göster
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                selectedImageUri?.let {
                    item {
                        ImageCard(
                            model = it,
                            contentDescription = "",
                            modifier = Modifier
                                .size(160.dp)
                                .padding(end = 4.dp),
                            contentScale = ContentScale.Crop,
                            onDeleteClick = {
                                selectedImageUri = null // Tek bir resmi kaldır
                            }
                        )
                    }
                }

                // Birden fazla seçilmiş resimleri göster ve silinebilir hale getir
                items(selectedImageUris) { uri ->
                    ImageCard(
                        model = uri,
                        contentDescription = "",
                        modifier = Modifier
                            .size(160.dp)
                            .padding(end = 4.dp),
                        contentScale = ContentScale.Crop,
                        onDeleteClick = {
                            selectedImageUris = selectedImageUris.toMutableList().apply {
                                remove(uri)
                            }
                        }
                    )
                }
            }
        }


        // Diğer bileşenler fotoğraftan bağımsız gösterilecek
        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentName(
                labelValue = stringResource(id = R.string.name_text),
                painterResource(id = R.drawable.profile),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.NameChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.nameError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentTitle(
                labelValue = stringResource(id = R.string.title_text),
                painterResource(id = R.drawable.ic_title),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.TitleChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.titleError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentAddress(
                labelValue = stringResource(id = R.string.address_text),
                painterResource(id = R.drawable.address),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.AddressChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.addressError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentLocation(
                labelValue = "Bu özellik şu anda mevcut değil",
                painterResource = painterResource(id = R.drawable.address),
                latitude = selectedLatitude,
                longitude = selectedLongitude,
            )
            //BURADA NAVCONTROLLER İKLE GEÇİLEN EKRANDAN DÖNÜŞTE FORMDAKİ VERİLERİN KAYBEDİLMEMESİ GEREK.
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentPositive(
                labelValue = stringResource(id = R.string.positive_features_text),
                painterResource(id = R.drawable.positive),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.PositiveTitleChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.positiveTitleError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponentNegative(
                labelValue = stringResource(id = R.string.negative_features_text),
                painterResource(id = R.drawable.negative),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.NegativeTitleChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.negativeTitleError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            PriceComponent(
                labelValue = stringResource(id = R.string.price_text),
                painterResource(id = R.drawable.money),
                onTextChanged = {
                    addViewModel.onEvent(AddUIEvent.PriceChanged(it))
                },
                errorStatus = addViewModel.addUIState.value.priceError
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            IsPrivateChecked(
                isChecked = addViewModel.addUIState.value.isPrivate,
                text = stringResource(R.string.privateCheckText),
                onCheckedChange = {
                    addViewModel.onEvent(AddUIEvent.PrivateChecked(it))
                }
            )
        }
    }
    if (showSuccessDialog) {
        LaunchedEffect(Unit) {
            delay(4000) // 4 saniye bekleme
            enableConfirmButton = true
        }
    }
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text(text = "Başarılı") },
            text = { Text(text = "Veritabanına kayıt işlemi başarılı.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (enableConfirmButton) {
                            showSuccessDialog = false
                            navController.navigate("main_screen") {
                                popUpTo("main_screen") { inclusive = true }
                            }
                        }
                    },
                    enabled = enableConfirmButton
                ) {
                    Text("Tamam")
                }
            }
        )
    }
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text(text = "Uyarı") },
            text = { Text(text = "Eğer butona basarsanız kaydedilmemiş veriler silinecek.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showAlertDialog = false
                        navController.navigate("main_screen") {
                            navController.navigateUp()
                        }
                    }
                ) {
                    Text("Evet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text("Hayır")
                }
            }
        )
    }
    if (toastMessage) {
        LaunchedEffect(toastMessage) {
            Toast.makeText(context, "Eksik veri bulunmakta", Toast.LENGTH_SHORT).show()
            toastMessage = false
        }
    }
}


//geri butonuna tıklandığında save'lemeden çıkmak istiyor musunuz? Mesajı gelecek
//eğer Yes denirse değerler kaydedilmeden tüm değerler False'a null'a çekilip
//ilk hale getirilecek ve kullanıcı ana sayfaya atılacak, eğer no derse hiçbir şey yapmadan
//mesaj kapatılacak

//bunun için => ya değerler boş mu kontrolü yapılır ya da her durumda bu bildiri gelir.


    //Burada yapılacak işler=
    // Name (req),
    // title (req),
    // adress= Haritadan seçilecek,(Not req),
    // pozitif yönler (Not req),
    // negatif yönler (Not req),
    // Fiyat (BURADA ARALIK SEÇTİRME OLACAK) (Not req)
    //public private CHECKBOX (required)

    //YUKARIDAKİ TASARIMDAKİ İTEMLAR FİRESTORE'A KAYDEDİLECEK
    //FİRESTORE'A KAYDEDİLEN VERİLER NASIL KAYDEDİLECEK?
    //FİRESTORE'DAKİ VERİLER KİŞİLERLE NASIL BAĞDAŞLAŞTIRILACAK?
    //Firestore'daki fotolar nasıl kayıt edilecek?
    //VİEWMODEL KESİNLİKLE KULLANILACAK

/*
    Spacer(Modifier.height(16.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
    Spacer(Modifier.height(8.dp))
    MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
        painterResource(id = R.drawable.message),
        onTextChanged = {
            addViewModel.onEvent(AddUIEvent.EmailChanged(it))
        },
        errorStatus = addViewModel.addUIState.value.emailError
    )
}

 */