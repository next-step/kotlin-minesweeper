package minesweeper.domain

sealed interface Block {
    object LandMine : Block
    class Normal : Block
}
