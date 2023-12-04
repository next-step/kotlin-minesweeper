package domain.board

sealed interface Cell {
    class Mine : Cell

    class Tile : Cell
}
