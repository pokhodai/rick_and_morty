package com.movie.rick_and_morty.screens.characters.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.screens.characters.DescriptionCardCharacters
import com.movie.rick_and_morty.tools.enums.StatusCharacter
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun CharacterScreen(id: String?) {

    val viewModel = hiltViewModel<CharacterViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = rememberAsyncImagePainter(model = viewModel.character.value?.image),
            contentDescription = "Image.Character",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Box(
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                when (viewModel.character.value?.status
                                    ?: StatusCharacter.UNKNOWN) {
                                    StatusCharacter.ALIVE -> Color.Green
                                    StatusCharacter.DEAD -> Color.Red
                                    StatusCharacter.UNKNOWN -> Color.Yellow
                                }
                            )
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = (viewModel.character.value?.status ?: StatusCharacter.UNKNOWN).text,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

            IconButton(onClick = { viewModel.onClickFavorite() }) {
                Image(
                    painter = if (viewModel.isFavorite) painterResource(id = R.drawable.ic_favorite_active) else painterResource(
                        id = R.drawable.ic_favorite_inactive
                    ), contentDescription = "Image.Favorite"
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 5.dp)
                .background(shape = RoundedCornerShape(20), color = Color.White)
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DescriptionCardCharacters(
                stringResource(id = R.string.characters_screen_race),
                viewModel.character.value?.species ?: ""
            )
            DescriptionCardCharacters(
                stringResource(id = R.string.characters_screen_gender),
                viewModel.character.value?.gender ?: "",
                bottomPadding = 8.dp
            )
        }
    }
}
