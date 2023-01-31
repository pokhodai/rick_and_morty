package com.movie.rick_and_morty.screens.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.ui.theme.asaRussFontFamily
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.tools.enums.StatusCharacter
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun CharactersScreen(
    onNavigateToCharacterDetailsScreen: () -> Unit
) {
    val viewModel = hiltViewModel<CharactersViewModel>()
    val systemUiController = rememberSystemUiController()

    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = LightGrey,
            darkIcons = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.characters_screen_title),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = asaRussFontFamily
                )
            )
        }

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = viewModel::onSwipeRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            CharactersListGridView(viewModel)
        }
    }
}

@Composable
private fun CharactersListGridView(viewModel: CharactersViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        items(viewModel.characters) { item ->
            CharactersCardView(
                name = item.name,
                status = item.status,
                race = item.species,
                gender = item.gender,
                image = item.image
            )
        }
    }
}

@Composable
private fun CharactersCardView(
    name: String,
    status: StatusCharacter,
    race: String,
    gender: String,
    image: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White),

        ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Image.Card.Characters",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .fillMaxWidth(0.5f),
                    text = name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 6.dp)
            ) {
                Box(
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                when (status) {
                                    StatusCharacter.ALIVE -> Color.Green
                                    StatusCharacter.DEAD -> Color.Red
                                    StatusCharacter.UNKNOWN -> Color.Yellow
                                }
                            )
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = status.text,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

            DescriptionCardCharacters(stringResource(id = R.string.characters_screen_race), race)
            DescriptionCardCharacters(stringResource(id = R.string.characters_screen_gender), gender, bottomPadding = 8.dp)
        }
    }
}

@Composable
private fun DescriptionCardCharacters(title: String, text: String, bottomPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = bottomPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, style = TextStyle(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp
            )
        )

        Text(
            text = text, style = TextStyle(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp
            )
        )
    }
}