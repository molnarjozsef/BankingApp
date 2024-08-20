package com.jozsefmolnar.bankingapp

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavHostController

@Composable
actual fun BackGestureHandler(
    navController: NavHostController,
    content: @Composable () -> Unit,
) {
    var offsetStart by remember { mutableStateOf(-1f) }
    var offsetFinish by remember { mutableStateOf(-1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        if (offset.x <= GESTURE_ACCEPTED_START) offsetStart = offset.x
                    },

                    onDragEnd = {
                        val isOnBackGestureActivated = offsetStart in 0f..GESTURE_ACCEPTED_START
                                && offsetFinish > GESTURE_MINIMUM_FINISH

                        if (isOnBackGestureActivated) {
                            navController.navigateUp()
                        }

                        offsetStart = -1f
                        offsetFinish = -1f
                    },

                    onHorizontalDrag = { change, _ -> offsetFinish = change.position.x }
                )
            }
    ) {
        content()
    }
}

private const val GESTURE_ACCEPTED_START = 80f
private const val GESTURE_MINIMUM_FINISH = 300f
