package minesweeper.domain.block

class Mine(private val position: Position) : Block {

    override fun getPosition(): Position {
        return position
    }
}
