package minesweeper.domain.cell

enum class Symbol {
    LANDMINE,
    CLOSED,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    companion object {
        fun from(numberOfAdjacentMines: NumberOfAdjacentMines): Symbol =
            when (numberOfAdjacentMines) {
                NumberOfAdjacentMines.ZERO -> ZERO
                NumberOfAdjacentMines(1) -> ONE
                NumberOfAdjacentMines(2) -> TWO
                NumberOfAdjacentMines(3) -> THREE
                NumberOfAdjacentMines(4) -> FOUR
                NumberOfAdjacentMines(5) -> FIVE
                NumberOfAdjacentMines(6) -> SIX
                NumberOfAdjacentMines(7) -> SEVEN
                NumberOfAdjacentMines(8) -> EIGHT
                else -> throw IllegalArgumentException("입력한 인접 지뢰 갯수에 대한 적절한 심볼을 찾을 수 없습니다: numberOfAdjacentMines=$numberOfAdjacentMines")
            }
    }
}
