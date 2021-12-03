package minesweeper.domain.block

data class Mines(private val position: Position) : Block(position) {
    override fun isMines(): Boolean = true
}
