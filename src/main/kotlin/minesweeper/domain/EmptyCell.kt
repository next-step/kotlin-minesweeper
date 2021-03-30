package minesweeper.domain

internal class EmptyCell : Cell() {
    override val hasMine = false
}
