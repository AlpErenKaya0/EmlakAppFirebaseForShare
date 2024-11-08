package com.example.emlakappfirebase.utils

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emlakappfirebase.ui.theme.AccentColor
import com.example.emlakappfirebase.ui.theme.Primary
import com.example.emlakappfirebase.ui.theme.Secondary
import com.example.emlakappfirebase.R
import com.example.emlakappfirebase.ui.screens.navigation.NavigationItem
import com.example.emlakappfirebase.ui.theme.BgColor
import com.example.emlakappfirebase.ui.theme.GrayColor
import com.example.emlakappfirebase.ui.theme.TextColor
import com.example.emlakappfirebase.ui.theme.WhiteColor
import com.example.emlakappfirebase.ui.theme.componentShapes
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter


@Composable
    fun NormalTextComponent(value: String) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ), color = colorResource(id = R.color.colorText),
            textAlign = TextAlign.Center
        )
    }
//Burada TopBar yapacağız daha mantıklı olacak, Geri tuşu bunun içinde olacak, bu geri tuşu da opsiyonel eklenecek

    @Composable
    fun HeadingTextComponent(value: String) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ), color = colorResource(id = R.color.colorText),
            textAlign = TextAlign.Center
        )
    }
//bu Terms  Conditions'ta kullanıldı login ve register'da yapmak mantıklı mı bilmiyorum
// OPSİYONEL backButton durumuyla yapılması en mantıklısı olacaktır. HeadingComp kaldırılır sonrasında da

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    value:String
)
{
    IconButton(
        onClick = { onBackPressed() },
        modifier = modifier
            .padding(16.dp)
            .size(40.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Button",
            tint = Color.Black
        )
    }
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}
/*
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    backButtonAction: (() -> Unit)? = null,
    title: String? = null
) {
    TopAppBar(
        title = {
            title?.let {
                Text(
                    text = it,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center
                    ),
                    color = colorResource(id = R.color.colorText)
                )
            }
        },
        navigationIcon = if (showBackButton && backButtonAction != null) {
            {
                IconButton(onClick = backButtonAction) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.Black
                    )
                }
            }
        } else null,
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White,
        modifier = modifier.height(56.dp)
    )
}
*/
    @Composable
    fun MyTextFieldComponent(
        labelValue: String, painterResource: Painter,
        onTextChanged: (String) -> Unit,
        errorStatus: Boolean = false
    ) {

        val textValue = remember {
            mutableStateOf("")
        }
        val localFocusManager = LocalFocusManager.current

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                focusedContainerColor = BgColor, //?
            ),
            /*
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                backgroundColor = BgColor
            ),*/
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextChanged(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = "")
            },
            isError = !errorStatus
        )
    }

    @Composable
    fun PasswordTextFieldComponent(
        labelValue: String, painterResource: Painter,
        onTextSelected: (String) -> Unit,
        errorStatus: Boolean = false
    ) {

        val localFocusManager = LocalFocusManager.current
        val password = remember {
            mutableStateOf("")
        }

        val passwordVisible = remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                focusedContainerColor = BgColor, //?
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            maxLines = 1,
            value = password.value,
            onValueChange = {
                password.value = it
                onTextSelected(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = "")
            },
            trailingIcon = {

                val iconImage = if (passwordVisible.value) {

                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                val description = if (passwordVisible.value) {
                    stringResource(id = R.string.hide_password)
                } else {
                    stringResource(id = R.string.show_password)
                }

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }

            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            isError = !errorStatus
        )
    }

@Composable
    fun CheckboxComponent(
        value: String,
        onTextSelected: (String) -> Unit,
        onCheckedChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val checkedState = remember {
                mutableStateOf(false)
            }

            Checkbox(checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = !checkedState.value
                    onCheckedChange.invoke(it)
                })

            ClickableTextComponent(value = value, onTextSelected)
        }
    }

    @Composable
    fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
        val initialText = "By continuing you accept our "
        val privacyPolicyText = "Privacy Policy"
        val andText = " and "
        val termsAndConditionsText = "Term of Use"

        val annotatedString = buildAnnotatedString {
            append(initialText)
            withStyle(style = SpanStyle(color = Primary)) {
                pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
                append(privacyPolicyText)
            }
            append(andText)
            withStyle(style = SpanStyle(color = Primary)) {
                pushStringAnnotation(
                    tag = termsAndConditionsText,
                    annotation = termsAndConditionsText
                )
                append(termsAndConditionsText)
            }
        }

        ClickableText(text = annotatedString, onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                        onTextSelected(span.item)
                    }
                }

        })
    }

    @Composable
    fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            onClick = {
                onButtonClicked.invoke()
            },
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(50.dp),
            enabled = isEnabled
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }

        }
    }

    @Composable
    fun DividerTextComponent() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = GrayColor,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.or),
                fontSize = 18.sp,
                color = TextColor
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = GrayColor,
                thickness = 1.dp
            )
        }
    }


    @Composable
    fun ClickableLoginTextComponent(
        tryingToLogin: Boolean = true,
        onTextSelected: (String) -> Unit
    ) {
        val initialText =
            if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
        val loginText = if (tryingToLogin) "Login" else "Register"

        val annotatedString = buildAnnotatedString {
            append(initialText)
            withStyle(style = SpanStyle(color = Primary)) {
                pushStringAnnotation(tag = loginText, annotation = loginText)
                append(loginText)
            }
        }

        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp),
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            text = annotatedString,
            onClick = { offset ->

                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.also { span ->
                        Log.d("ClickableTextComponent", "{${span.item}}")

                        if (span.item == loginText) {
                            onTextSelected(span.item)
                        }
                    }

            },
        )
    }

    @Composable
    fun UnderLinedTextComponent(value: String) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ), color = colorResource(id = R.color.colorGray),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

    }

//bu AppToolBar Logout Butonu olmadan yapılacak ki her yerde kullanılsın.
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppToolbar(
        toolbarTitle: String, logoutButtonClicked: () -> Unit,
        navigationIconClicked: () -> Unit
    ) {

        TopAppBar(
            //aşağidaki colors'ın normaldeki hali  backgroundColor = Primary, buydu ama arıza çıktı
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary),
            title = {
                Text(
                    text = toolbarTitle, color = WhiteColor
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navigationIconClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(R.string.menu),
                        tint = WhiteColor
                    )
                }

            },
            actions = {
                IconButton(onClick = {
                    logoutButtonClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ExitToApp,
                        contentDescription = stringResource(id = R.string.logout),
                    )
                }
            }
        )
    }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    toolbarTitle: String,
    backButtonClicked: () -> Unit,
    saveButtonClicked: () -> Unit
) {

    TopAppBar(
        //aşağidaki colors'ın normaldeki hali  backgroundColor = Primary, buydu ama arıza çıktı
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary),
        title = {
            Text(
                text = toolbarTitle, color = WhiteColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                backButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = WhiteColor
                )
            }

        },
        actions = {
            Button(onClick = {
                saveButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = stringResource(id = R.string.save),
                )
            }
        }
    )
}

    @Composable
    fun NavigationDrawerHeader(value: String?) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(Primary, Secondary)
                    )
                )
                .fillMaxWidth()
                .height(180.dp)
                .padding(32.dp)
        ) {

            NavigationDrawerText(
                title = value ?: stringResource(R.string.navigation_header), 28.sp, AccentColor
            )

        }
    }

    @Composable
    fun NavigationDrawerBody(
        navigationDrawerItems: List<NavigationItem>,
        onNavigationItemClicked: (NavigationItem) -> Unit
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(navigationDrawerItems) {
                NavigationItemRow(item = it, onNavigationItemClicked)
            }

        }
    }

    @Composable
    fun NavigationItemRow(
        item: NavigationItem,
        onNavigationItemClicked: (NavigationItem) -> Unit
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigationItemClicked.invoke(item)
                }
                .padding(all = 16.dp)
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.description,
            )

            Spacer(modifier = Modifier.width(18.dp))

            NavigationDrawerText(title = item.title, 18.sp, Primary)


        }
    }

    @Composable
    fun NavigationDrawerText(title: String, textUnit: TextUnit, color: Color) {

        val shadowOffset = Offset(4f, 6f)

        Text(
            text = title, style = TextStyle(
                color = androidx.compose.ui.graphics.Color.Black,
                fontSize = textUnit,
                fontStyle = FontStyle.Normal,
                shadow = Shadow(
                    color = Primary,
                    offset = shadowOffset, 2f
                )
            )
        )
    }

@Composable
fun ImageCard(
    model: Any?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = model,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxWidth(),
                contentScale = contentScale
            )

            Icon(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable {
                        onDeleteClick()
                    }
            )
        }
    }
}
@Composable
fun MyTextFieldComponentName(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }


    val isValid = textValue.value.length > 3
    val borderColor = if (isValid) Color.Blue else Color.Red

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= 20) {
                textValue.value = it
                onTextChanged(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !isValid || errorStatus
    )
}

@Composable
fun MyTextFieldComponentTitle(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    // Renk durumu için bir state tanımlayın
    val isValid = textValue.value.length >= 6 // 6 karakter kontrolü
    val borderColor = if (isValid) Color.Blue else Color.Red // Kırmızı veya mavi

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor, //?
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= 60) { // Maksimum 20 karakter kontrolü
                textValue.value = it
                onTextChanged(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !isValid || errorStatus // Hata durumu
    )
}

@Composable
fun MyTextFieldComponentAddress(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    // Renk durumu için bir state tanımlayın
    val isValid = textValue.value.length >= 6 // 6 karakter kontrolü
    val borderColor = if (isValid) Color.Blue else Color.Red // Kırmızı veya mavi

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor, //?
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= 60) { // Maksimum 20 karakter kontrolü
                textValue.value = it
                onTextChanged(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !isValid || errorStatus // Hata durumu
    )
}

@Composable
fun MyTextFieldComponentLocation(
    labelValue: String,
    painterResource: Painter,
    latitude: Double?,
    longitude: Double?,
    errorStatus: Boolean = false
) {
    val textValue = remember {
        mutableStateOf("")
    }

    // Update the text value when latitude and longitude are provided
    LaunchedEffect(latitude, longitude) {
        if (latitude != null && longitude != null) {
            textValue.value = "Lat: $latitude, Lng: $longitude"
        }
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small)
                ,value = textValue.value,
        onValueChange = {  },
        readOnly = true,
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor,
        ),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
    )
}


@Composable
fun MyTextFieldComponentPositive(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    // Renk durumu için bir state tanımlayın
    val isValid = textValue.value.length >= 3 // 3 karakter kontrolü
    val borderColor = if (isValid) Color.Green else Color.Red // Kırmızı veya yeşil

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor, //?
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= 80) { // Maksimum 20 karakter kontrolü
                textValue.value = it
                onTextChanged(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !isValid || errorStatus // Hata durumu
    )
}

@Composable
fun MyTextFieldComponentNegative(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    // Renk durumu için bir state tanımlayın
    val isValid = textValue.value.length >= 3 // 3 karakter kontrolü
    val borderColor = if (isValid) Color.Blue else Color.Red // Kırmızı veya mavi

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor, //?
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= 80) { // Maksimum 20 karakter kontrolü
                textValue.value = it
                onTextChanged(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !isValid || errorStatus // Hata durumu
    )
}

@Composable
fun PriceComponent(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }

    val borderColor = if (textValue.value.length > 3) Color.Blue else Primary

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            focusedContainerColor = BgColor,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = { input ->
            val filteredInput = input.filter { it.isDigit() }
            if (filteredInput.length <= 20) {
                textValue.value = filteredInput
                onTextChanged(filteredInput)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}




@Composable
fun IsPrivateChecked(
    isChecked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit // Add a parameter for the checked change callback
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, // Align items vertically centered
        modifier = Modifier.padding(16.dp) // Add some padding around the row
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange, // Use the passed function to handle changes
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue, // Customize the checked color
                uncheckedColor = Color.Gray // Customize the unchecked color
            )
        )
        Spacer(modifier = Modifier.width(8.dp)) // Add some space between checkbox and text
        Text(text = text, style = MaterialTheme.typography.body1) // Display the text
    }
}
@Composable
fun HouseCard(
    name: String,
    imageUrls: List<Uri>,
    topText: String,
    middleText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
    onHeartClick: (Boolean) -> Unit, // Kalp tıklama fonksiyonu
    onCardClick: () -> Unit // Kart tıklama fonksiyonu
) {
    var isHeartFilled by remember { mutableStateOf(false) } // Kalp dolu mu kontrolü

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onCardClick() } // Kart tıklama işlevi
        , // Kart tıklama fonksiyonu
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Kullanıcı ismi
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.secondary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )

            // Kaydırılabilir resim gösterimi
            if (imageUrls.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(imageUrls) { imageUri ->
                        Image(
                            painter = rememberImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
                        )
                    }
                }
            } else {
                // Resim yoksa bir mesaj göster
                Text(
                    text = "No images available",
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Metin bilgileri
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = topText, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = middleText, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = bottomText, fontSize = 14.sp, modifier = Modifier.align(Alignment.End))
            }

            // Kalp simgesi
            IconButton(
                onClick = {
                    isHeartFilled = !isHeartFilled // Kalp durumunu değiştir
                    onHeartClick(isHeartFilled) // Kalp durumu fonksiyonunu çağır
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = if (isHeartFilled) {
                        Icons.Default.Favorite // Dolu kalp
                    } else {
                        Icons.Default.FavoriteBorder // Boş kalp
                    },
                    contentDescription = "Heart",
                    tint = if (isHeartFilled) Color.Red else Color.Gray,
                    modifier = Modifier.size(24.dp) // Kalp boyutunu ayarlama
                )
            }
        }
    }
}
@Composable
fun HouseCardWithTrash(
    name: String,
    imageUrls: List<Uri>,
    topText: String,
    middleText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
    onTrashClick: (Boolean) -> Unit, // Çöp tıklama fonksiyonu
    onCardClick: () -> Unit // Kart tıklama fonksiyonu
) {
    var isTrashFilled by remember { mutableStateOf(false) } // Çöp dolu mu kontrolü

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onCardClick() }, // Kart tıklama işlevi
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Kullanıcı ismi
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.secondary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )

            // Kaydırılabilir resim gösterimi
            if (imageUrls.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(imageUrls) { imageUri ->
                        Image(
                            painter = rememberImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
                        )
                    }
                }
            } else {
                // Resim yoksa bir mesaj göster
                Text(
                    text = "No images available",
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Metin bilgileri
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = topText, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = middleText, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = bottomText, fontSize = 14.sp, modifier = Modifier.align(Alignment.End))
            }

            // Çöp simgesi
            IconButton(
                onClick = {
                    isTrashFilled = !isTrashFilled // Çöp durumunu değiştir
                    onTrashClick(isTrashFilled) // Çöp durumu fonksiyonunu çağır
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = if (isTrashFilled) {
                        Icons.Default.Delete // Dolu çöp
                    } else {
                        Icons.Default.DeleteOutline // Boş çöp
                    },
                    contentDescription = "Trash",
                    tint = if (isTrashFilled) Color.Red else Color.Gray,
                    modifier = Modifier.size(24.dp) // Çöp boyutunu ayarlama
                )
            }
        }
    }
}

@Composable
fun HouseCardHeartless(
    name: String,
    imageUrls: List<Uri>,
    topText: String,
    middleText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onCardClick() },
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Kullanıcı ismi
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.secondary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )

            // Kaydırılabilir resim gösterimi
            if (imageUrls.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(imageUrls) { imageUri ->
                        Image(
                            painter = rememberImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
                        )
                    }
                }
            } else {
                // Resim yoksa bir mesaj göster
                Text(
                    text = "No images available",
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Metin bilgileri
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = topText, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = middleText, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = bottomText, fontSize = 14.sp, modifier = Modifier.align(Alignment.End))
            }
        }
    }
}
