package minesweeper.domain

sealed interface Block {
    object LandMine : Block
    object Normal : Block
}
