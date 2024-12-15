package minesweeper.domain

interface Cell {
    fun row(): Int

    fun column(): Int

    fun display(): String
}
