package minesweeper.domain

sealed class Pin(private val mark: String) {
    fun getMark(): String {
        return mark
    }
}
