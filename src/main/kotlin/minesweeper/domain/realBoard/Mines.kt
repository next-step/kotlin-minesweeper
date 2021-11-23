package minesweeper.domain.realBoard

@JvmInline
value class Mines(private val mine: Int) {

    companion object {
        fun of(mines: Int): Mines = Mines(mines)
    }
}
