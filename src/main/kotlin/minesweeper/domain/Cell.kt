package minesweeper.domain

interface Cell {
    fun location(): Location

    fun symbol(): Symbol
}
