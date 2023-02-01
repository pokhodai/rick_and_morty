package com.movie.rick_and_morty.screens.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.ui.theme.Green
import com.movie.rick_and_morty.ui.theme.LightGreen
import com.movie.rick_and_morty.ui.theme.LightGreen2
import com.movie.rick_and_morty.ui.theme.asaRussFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoardingScreen(onNavigateToMain: () -> Unit) {
    val viewModel = hiltViewModel<BoardingViewModel>()
    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current

    fun navigateToMain() {
        onNavigateToMain.invoke()
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = LightGreen,
            darkIcons = false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen)
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_union),
                contentDescription = "Boarding.Image.Union"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.boarding_title),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = asaRussFontFamily,
                    ),
                )

                Box(Modifier.padding(top = 20.dp)) {
                    BasicTextField(
                        modifier = Modifier
                            .background(
                                Color.LightGray,
                                RoundedCornerShape(percent = 50),
                            )
                            .size(width = 220.dp, height = 40.dp)
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = viewModel.nameState,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif
                        ),
                        decorationBox = { innerTextField ->
                            if (viewModel.nameState.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.boarding_hint),
                                    style = TextStyle(
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Gray
                                    )
                                )
                            }
                            innerTextField()
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                navigateToMain()
                                keyboardController?.hide()
                            }
                        ),
                        onValueChange = {
                            viewModel.onChangeName(it)
                        })
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .size(160.dp),
                painter = painterResource(id = R.drawable.pngwing),
                contentDescription = "Boarding.Image.Wing",
            )
        }

        EmptyView(
            modifier = Modifier
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = viewModel.titleState,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black
                )
            )
        }

        EmptyView(
            modifier = Modifier
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Green,
                    disabledBackgroundColor = LightGreen2
                ),
                enabled = viewModel.enabledState,
                onClick = {
                    navigateToMain()
                }) {
                Text(
                    text = stringResource(id = R.string.boarding_btn),
                    style = TextStyle(color = Color.White)
                )
            }
        }
        EmptyView(
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun EmptyView(modifier: Modifier) {
    Box(modifier = modifier.defaultMinSize(minHeight = 20.dp))
}