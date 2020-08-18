package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _grid = mutableListOf<Char>()
    val grid: List<Char> get() = _grid

    init {
        repeat(boardSize.get()) {
            _grid.add(MineType.NONE.symbol)
        }
        mineIndexes.forEach {
            _grid[it] = MineType.MINE.symbol
        }
    }
}
