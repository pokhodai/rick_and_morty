package com.movie.rick_and_morty.screens.characters.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailsModal() {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(sheetState = modalSheetState,
        sheetContent = {
            Column() {

            }
        }) {

    }
}