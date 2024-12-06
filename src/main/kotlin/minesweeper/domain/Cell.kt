package minesweeper.domain

sealed class Cell

class Mine : Cell()

class Land : Cell()
