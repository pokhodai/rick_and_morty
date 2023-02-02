package com.movie.rick_and_morty.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun FavoritesScreen(onNavigateToCharactersFavoriteScreen: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 40.dp)
    ) {
        item {
            Box(modifier = Modifier.clickable {
                onNavigateToCharactersFavoriteScreen.invoke()
            }) {
                FavoriteCard(text = "Characters", id = R.drawable.ic_favorites_characters)
            }
        }
        item {
            Box() {
                FavoriteCard(text = "Episodes", id = R.drawable.ic_favorites_characters)
            }
        }
    }
}

@Composable
private fun FavoriteCard(text: String, id: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                painter = painterResource(id = id),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black
                )
            )
        }
    }
}