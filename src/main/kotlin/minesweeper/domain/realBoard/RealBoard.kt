package minesweeper.domain.realBoard

class RealBoard(val width: Width, val height: Height, val mines: Mines) {

    companion object {
        fun of(width: Int, height: Int, mines: Int): RealBoard =
            RealBoard(Width.of(width), Height.of(height), Mines.of(mines))
    }
}
