package com.example.emlakappfirebase.viewmodels
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emlakappfirebase.koin.di.repos.AddRepository
import com.example.emlakappfirebase.viewmodels.events.AddUIEvent
import com.example.emlakappfirebase.viewmodels.states.AddUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val addRepository: AddRepository) : ViewModel() {

    var addUIState = mutableStateOf(AddUIState())
    var isButtonEnable = mutableStateOf(false)

    fun clearFields() {
        addUIState.value = AddUIState()
    }

    fun onEvent(event: AddUIEvent) {
        when (event) {
            is AddUIEvent.PhotosUri -> {
                addUIState.value = addUIState.value.copy(photosUri = event.photosUri)
            }
            is AddUIEvent.NameChanged -> {
                addUIState.value = addUIState.value.copy(name = event.name)
            }
            is AddUIEvent.TitleChanged -> {
                addUIState.value = addUIState.value.copy(title = event.title)
            }
            is AddUIEvent.AddressChanged -> {
                addUIState.value = addUIState.value.copy(address = event.address)
            }
            is AddUIEvent.NegativeTitleChanged -> {
                addUIState.value = addUIState.value.copy(negativeTitle = event.negativeTitle)
            }
            is AddUIEvent.  PositiveTitleChanged -> {
                addUIState.value = addUIState.value.copy(positiveTitle = event.positiveTitle)
            }
            is AddUIEvent.PriceChanged -> {
                addUIState.value = addUIState.value.copy(price = event.price)
            }
            is AddUIEvent.PrivateChecked -> {
                addUIState.value = addUIState.value.copy(isPrivate = event.isPrivate)

            }
            else -> {}
        }
    }

    fun isButtonEnable(): Boolean {
        val name = addUIState.value.name
        val title = addUIState.value.title
        val address = addUIState.value.address
        val negative = addUIState.value.negativeTitle
        val positive = addUIState.value.positiveTitle
        val price = addUIState.value.price


        return !name.isNullOrBlank() && name.length >= 3 &&
                !title.isNullOrBlank() && title.length >= 3 &&
                !address.isNullOrBlank() && address.length >= 3 &&
                !negative.isNullOrBlank() && negative.length >= 3 &&
                !positive.isNullOrBlank() && positive.length >= 3 &&
                !price.isNullOrBlank() && price.length >= 3
    }

    fun saveDataToFirestore(
        title: String,
        name: String,
        address: String,
        photos: List<Uri>,
        positiveTitle:String,
        negativeTitle:String,
        price: String,
        isPrivate:Boolean) {

        viewModelScope.launch(Dispatchers.IO) {

            addRepository.saveDataToFirestore(title,
                name,
                address,
                photos,
                positiveTitle,
                negativeTitle,
                price,
                isPrivate
                )
        }
    }


    /*
    fun buttonIsEnable(): Boolean {
        val name = addUIState.value.name
        val title = addUIState.value.title
        val address = addUIState.value.address

        return !name.isNullOrBlank() && !title.isNullOrBlank() && !address.isNullOrBlank()
    }
    */
}

   /* private fun validateDataWithRules() {
        val fNameResult = AddValidator.validateName(
            fName = addUIState.value.name
        )

        val lNameResult = AddValidator.validateAddress(
            lName = registrationUIState.value.lastName
        )

        val emailResult = AddValidator.validateTitle(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = addUIState.value.
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        addUIState.value = addUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }
*/

    /*
    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = addUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = addUIState.value.password
        )

        addUIState.value = addUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
*/

/*
class AddViewModel : ViewModel() {
    var addUIState = mutableStateOf(AddUIState())
    //var allValidationsPassed by mutableStateOf(false)
    fun onEvent(event: AddUIEvent) {
        when (event) {
            is AddUIEvent.EmailChanged -> {
                addUIState.value = addUIState.value.copy(
                    email = event.email
                )
            }

            is AddUIEvent.PasswordChanged -> {
                addUIState.value = addUIState.value.copy(
                    password = event.password
                )
            }

            is AddUIEvent.LoginButtonClicked -> {
    //            login()
            }
        }
  //      validateLoginUIDataWithRules()

    }

    /*
    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = addUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = addUIState.value.password
        )

        addUIState.value = addUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
*/
    }

/*
    // Firestore saving logic
    private fun saveToFirestore() {
        saveInProgress = true

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val firestore = FirebaseFirestore.getInstance()
        val data = mutableMapOf<String, Any?>(
            "name" to addUIState.name,
            "title" to addUIState.title,
            "address" to addUIState.address,
            "location" to addUIState.location, // Nullable
            "positiveTitle" to addUIState.positiveTitle, // Nullable
            "negativeTitle" to addUIState.negativeTitle, // Nullable
            "price" to addUIState.price, // Nullable
            "isPrivate" to (addUIState.isPrivate ?: false), // Default to false if null
            "photosUri" to addUIState.photosUri // Nullable if no photos selected
        )

        userId?.let {
            firestore.collection("users").document(it).collection("posts")
                .add(data)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                    saveInProgress = false
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error writing document", e)
                    saveInProgress = false
                }
        } ?: run {
            Log.w(TAG, "User is not authenticated")
            saveInProgress = false
        }
    }
    */
}
*/

/*
class AddViewModel: ViewModel() {
    //CreateFirebaseCollection()
    //Firestore'a kurallı bir şekilde kullanıcı ile ilişkilendirilmiş
    //veriler Storage ile gönderilecek

    //Adreslerde, textlerde vs karakter sınırlaması getirmek lazım ONU da Instance'tan getiririz
    //error fieldlarını da gircez

    private val TAG = LoginViewModel::class.simpleName
    var addUIState = mutableStateOf(AddUIState())
    var allValidationsPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: AddUIEvent) {
        when (event) {
            is AddUIEvent.NameChanged -> {
                addUIState.value = addUIState.value.copy(
                    name = event.name
                )
            }

            is AddUIEvent.SaveButtonClicked -> {
                addUIState.value = addUIState.value.copy(
                     = event.
                )
            }

            is AddUIEvent.SaveButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = addUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = addUIState.value.password
        )

        addUIState.value = addUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun login() {

        loginInProgress.value = true
        val email = addUIState.value.email
        val password = addUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value = false
                    //  PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                loginInProgress.value = false

            }

    }

}


 */