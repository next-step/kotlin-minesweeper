package minesweeper.domain.tile.state

abstract class Opened : Tile() {
    override val isChecked: Boolean = true
}
