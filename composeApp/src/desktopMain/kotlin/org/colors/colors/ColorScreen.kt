package org.colors.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qrgenerator.qrkitpainter.rememberQrKitPainter

@Composable
fun ColorGameScreen() {
    val viewModel = remember { ColorGameViewModel() }
    val targetState by viewModel.targetColorState.collectAsState()
    val guessState by viewModel.guessColorState.collectAsState()
    val message by viewModel.message.collectAsState()
    val textFieldColor = contrastingColor(guessState.color)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(guessState.color)
    ) {
        // Título o instrucciones
        Text(
            text = "Observa el color objetivo",
            style = MaterialTheme.typography.titleMedium,
            color = contrastingColor(guessState.color)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(targetState.color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.generateRandomTarget() },
            content = {
                Text(
                    text = "Generar Color",
                    color = contrastingColorButton(
                        contrastingColor(guessState.color),
                        guessState.color,
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = contrastingColor(guessState.color),
                contentColor = contrastingColor(targetState.color)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Adivina el color usando los valores RGB",
            style = MaterialTheme.typography.bodyMedium,
            color = contrastingColor(guessState.color)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Picker(
                value = guessState.red.toString(),
                colorString = "Red",
                textColor = textFieldColor,
                onValueChange = { newText ->
                    val newValue = newText.toIntOrNull() ?: 0
                    viewModel.updateGuess { copy(red = newValue.coerceIn(0, 255)) }
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Picker(
                value = guessState.green.toString(),
                colorString = "Green",
                textColor = textFieldColor,
                onValueChange = { newText ->
                    val newValue = newText.toIntOrNull() ?: 0
                    viewModel.updateGuess { copy(green = newValue.coerceIn(0, 255)) }
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Picker(
                value = guessState.blue.toString(),
                colorString = "Blue",
                textColor = textFieldColor,
                onValueChange = { newText ->
                    val newValue = newText.toIntOrNull() ?: 0
                    viewModel.updateGuess { copy(blue = newValue.coerceIn(0, 255)) }
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.checkGuess() },
            content = {
                Text(
                    text = "Comprobar",
                    color = contrastingColorButton(
                        contrastingColor(guessState.color),
                        guessState.color,
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = contrastingColor(guessState.color),
                contentColor = contrastingColor(targetState.color)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotEmpty()) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = contrastingColor(guessState.color)
            )
        }
    }
    QrGitHub(colorBackground = guessState.color)
}

@Composable
private fun Picker(
    value: String,
    colorString: String,
    textColor: Color,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = colorString,
                textAlign = TextAlign.Center,
                color = textColor.copy(alpha = 0.6f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            color = textColor,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.size(100.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = textColor,
            unfocusedBorderColor = textColor.copy(alpha = 0.6f),
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedPlaceholderColor = textColor.copy(alpha = 0.6f),
            unfocusedPlaceholderColor = textColor.copy(alpha = 0.6f),
            cursorColor = textColor
        )
    )
}

@Composable
private fun QrGitHub(colorBackground: Color) {
    val qr = rememberQrKitPainter(data = " ")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .background(
                    color = contrastingColor(colorBackground),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Text(
                text = "Github de la app",
                color = contrastingColorButton(
                    contrastingColor(colorBackground),
                    colorBackground,
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                painter = qr,
                contentDescription = "QR",
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp),
                tint = contrastingColorButton(
                    contrastingColor(colorBackground),
                    colorBackground,
                ),
            )
        }
    }
}

private fun contrastingColor(background: Color): Color =
    if (background.luminance() < 0.5f) Color.White else Color.Black

private fun contrastingColorButton(
    background: Color,
    textColor: Color
): Color =
    if (background.luminance() < 0.5f) textColor else Color.Black
