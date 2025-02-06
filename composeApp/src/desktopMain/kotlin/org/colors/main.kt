package org.colors

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import colors.composeapp.generated.resources.Designer
import colors.composeapp.generated.resources.Res
import org.colors.colors.ColorGameScreen
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Adivina el color",
        icon = painterResource(Res.drawable.Designer),
        state = rememberWindowState(
            placement = WindowPlacement.Maximized,
        )
    ) {
        ColorGameScreen()
    }
}

