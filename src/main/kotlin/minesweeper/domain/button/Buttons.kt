package minesweeper.domain.button

data class Buttons(
    private val buttons: List<Button>
) : List<Button> by buttons

fun List<Button>.toButtons(): Buttons =
    Buttons(this)
