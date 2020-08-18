package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _linear = mutableListOf<Char>()
    val grid: List<List<Char>>

    init {
        setupLinear(boardSize, mineIndexes)
        grid = convertToGrid(boardSize)
    }

    private fun convertToGrid(boardSize: BoardSize) = _linear.chunked(boardSize.getRow())

    private fun setupLinear(boardSize: BoardSize, mineIndexes: List<Int>) {
        repeat(boardSize.get()) {
            _linear.add(MineType.NONE.symbol)
        }
        mineIndexes.forEach {
            _linear[it] = MineType.MINE.symbol
        }
    }
}
