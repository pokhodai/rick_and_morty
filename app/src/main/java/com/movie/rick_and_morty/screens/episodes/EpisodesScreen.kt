package com.movie.rick_and_morty.screens.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.data.responses.EpisodesListResponse
import com.movie.rick_and_morty.ui.theme.LightGrey
import com.movie.rick_and_morty.ui.theme.asaRussFontFamily

@Composable
fun EpisodesScreen() {

    val systemUiController = rememberSystemUiController()
    val viewModel = hiltViewModel<EpisodesViewModel>()

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
            .background(LightGrey)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.episode_title),
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
            EpisodesList(viewModel.episodes)
        }
    }
}

@Composable
private fun EpisodesList(episodes: List<EpisodesListResponse.Episode>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        items(episodes) { item ->
            EpisodeCard(
                name = item.name,
                episode = item.episode,
                air_date = item.air_date
            )
        }
    }
}

@Composable
private fun EpisodeCard(
    name: String = "Name",
    episode: String = "S01E01",
    air_date: String = "December 2, 2013"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = name, style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = episode, style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = air_date, style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}