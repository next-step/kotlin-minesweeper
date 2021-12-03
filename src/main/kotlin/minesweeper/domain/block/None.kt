package minesweeper.domain.block

class None(private val position: Position) : Block {

    override fun getPosition(): Position {
        return position
    }
}
