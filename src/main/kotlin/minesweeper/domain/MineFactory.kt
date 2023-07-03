package minesweeper.domain

fun interface MineFactory {
    fun mines(): List<Coordinate>

    companion object {
        fun simpleMineFactory(mines: List<Coordinate>) = MineFactory { mines }
    }
}
