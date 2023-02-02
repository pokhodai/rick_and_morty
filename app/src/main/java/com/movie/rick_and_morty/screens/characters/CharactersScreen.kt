package com.movie.rick_and_morty.screens.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.tools.enums.StatusCharacter
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun CharactersScreen(
    onNavigateToCharacterScreen: (Int) -> Unit
) {
    val viewModel = hiltViewModel<CharactersViewModel>()

    val characters = viewModel.characters.collectAsLazyPagingItems()

    val isRefreshing = rememberSaveable { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey),
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = characters::refresh,
            modifier = Modifier.fillMaxSize()
        ) {
            CharactersListGridView(characters, isRefreshing, onNavigateToCharacterScreen)
        }
    }
}

@Composable
private fun CharactersListGridView(
    characters: LazyPagingItems<CharactersListResponse.Character>,
    isRefreshing: MutableState<Boolean>,
    onNavigateToCharacterScreen: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index]
            CharactersCardView(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    .clickable {
                        character?.id?.let { onNavigateToCharacterScreen.invoke(it) }
                    }, character = character
            )
        }
        when (characters.loadState.append) {
            is LoadState.NotLoading -> isRefreshing.value = false
            is LoadState.Loading -> isRefreshing.value = true
            is LoadState.Error -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Characters is Error!")
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersCardView(
    modifier: Modifier,
    character: CharactersListResponse.Character?
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = character?.image ?: ""),
                    contentDescription = "Image.Card.Characters",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = character?.name ?: "",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }
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
                                when (character?.status ?: StatusCharacter.UNKNOWN) {
                                    StatusCharacter.ALIVE -> Color.Green
                                    StatusCharacter.DEAD -> Color.Red
                                    StatusCharacter.UNKNOWN -> Color.Yellow
                                }
                            )
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = (character?.status ?: StatusCharacter.UNKNOWN).text,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

            DescriptionCardCharacters(
                stringResource(id = R.string.characters_screen_race),
                character?.species ?: ""
            )
            DescriptionCardCharacters(
                stringResource(id = R.string.characters_screen_gender),
                character?.gender ?: "",
                bottomPadding = 8.dp
            )
        }
    }
}

@Composable
fun DescriptionCardCharacters(title: String, text: String, bottomPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = bottomPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            text = title, style = TextStyle(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp
            )
        )

        Box(contentAlignment = Alignment.CenterEnd) {
            Text(
                text = text, style = TextStyle(
                    color = Color.Black,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            )
        }
    }
}