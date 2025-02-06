package org.colors.colors

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs
import kotlin.random.Random

class ColorGameViewModel {

    // Representa un color mediante sus componentes y una propiedad derivada para obtener la instancia Color
    data class ColorState(
        val red: Int = 0,
        val green: Int = 0,
        val blue: Int = 0,
    ) {
        val color: Color
            get() = Color(red, green, blue)
    }

    // Estado del color objetivo
    private val _targetColorState: MutableStateFlow<ColorState> = MutableStateFlow(randomColor())
    val targetColorState: StateFlow<ColorState> = _targetColorState

    // Estado del color que el usuario va armando (intento)
    private val _guessColorState: MutableStateFlow<ColorState> = MutableStateFlow(ColorState())
    val guessColorState: StateFlow<ColorState> = _guessColorState

    // Mensaje de resultado
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    // Genera un nuevo color objetivo y reinicia el intento y el mensaje
    fun generateRandomTarget() {
        _targetColorState.value = randomColor()
        _guessColorState.value = ColorState() // reiniciar intento
        _message.value = ""
    }

    // Actualiza el estado del intento utilizando la función copy para actualizar un componente en particular
    fun updateGuess(update: ColorState.() -> ColorState) {
        _guessColorState.value = _guessColorState.value.update()
    }

    // Compara el intento del usuario con el color objetivo
    fun checkGuess() {
        val guess = _guessColorState.value
        val target = _targetColorState.value
        val diffR = abs(guess.red - target.red)
        val diffG = abs(guess.green - target.green)
        val diffB = abs(guess.blue - target.blue)
        val percentR = 1 - (diffR / 255.0)
        val percentG = 1 - (diffG / 255.0)
        val percentB = 1 - (diffB / 255.0)
        val closeness = ((percentR + percentG + percentB) / 3) * 100

        if (guess == target) {
            _message.value = "¡Correcto! Acierto perfecto."
        } else {
            _message.value = "Estuviste a ${closeness.toInt()}% de acercarte al color."
        }
    }

    // Función auxiliar para generar un color aleatorio
    private fun randomColor(): ColorState {
        return ColorState(
            red = Random.nextInt(0, 256),
            green = Random.nextInt(0, 256),
            blue = Random.nextInt(0, 256)
        )
    }
}
