package model

class Board(private val boardSize: BoardSize) {
    private val _grid = mutableListOf<Char>()
    val grid: List<Char> get() = _grid

    fun make(mineIndexes: List<Int>) {
        repeat(boardSize.get()) {
            _grid.add(MineType.NONE.symbol)
        }
        mineIndexes.take(mineIndexes.size).map {
            _grid[it] = MineType.MINE.symbol
        }
    }
}
