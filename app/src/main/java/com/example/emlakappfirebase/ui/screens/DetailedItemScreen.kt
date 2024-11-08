/*
package com.example.emlakappfirebase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.emlakappfirebase.ui.screens.logic.HouseData

@Composable
fun DetailedItemScreen(
navController: NavController,
modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            // Kaydırılabilir fotoğraf galerisi
            if (houseData.photos.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(houseData.photos) { photoUri ->
                        Image(
                            painter = rememberImagePainter(data = photoUri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
                        )
                    }
                }
            } else {
                Text(
                    text = "No images available",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        item {
            // Başlık ve alt detaylar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Title
                Text(
                    text = houseData.title ?: "Title not available",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Name
                Text(
                    text = houseData.name ?: "Name not available",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Address
                Text(
                    text = houseData.address ?: "Address not available",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Positive and Negative Titles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = houseData.positiveTitle ?: "Positive Info",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = houseData.negativeTitle ?: "Negative Info",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Price
                Text(
                    text = houseData.price ?: "Price not available",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}
*/