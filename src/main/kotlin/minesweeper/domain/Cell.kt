package minesweeper.domain

interface Cell {
    fun location(): Location

    fun display(): String
}
