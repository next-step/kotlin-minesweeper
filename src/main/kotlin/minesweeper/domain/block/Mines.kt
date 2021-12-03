package minesweeper.domain.block

data class Mines(override val position: Position) : Block(position) {
    override fun isMines(): Boolean = true
}
