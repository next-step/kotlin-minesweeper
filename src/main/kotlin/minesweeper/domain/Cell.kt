package minesweeper.domain

sealed class Cell {
    object MineCell : Cell()
    object SafetyCell : Cell()
}
