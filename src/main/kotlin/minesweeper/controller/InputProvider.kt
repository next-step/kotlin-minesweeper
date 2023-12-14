package minesweeper.controller

interface InputProvider {
    fun height(): Int

    fun width(): Int

    fun mineCount(): Int
}
