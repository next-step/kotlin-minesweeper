package domain

class Board private constructor(
    val width: Int,
    private val height: Int,
    private val mineCount: Int = 0
) {
    val boardInfo: Map<Coordinate, Block> get() = _boardInfo.toMap()

    private val size = width * height
    private val _boardInfo = LinkedHashMap<Coordinate, Block>(size)

    init {
        initBoard()
    }

    private fun initBoard() {
        val coordinates = createCoordinates().shuffled()
        val mines = createMines(coordinates)
        val points = (mines + createGenerals(coordinates)).sortedBy { it.first }

        _boardInfo.putAll(points)
        mines.forEach { notifySetMine(it.first) }
    }

    private fun createMines(coordinates: List<Coordinate>) =
        coordinates.take(mineCount).map { Pair(it, Block(BlockType.MINE)) }

    private fun createGenerals(coordinates: List<Coordinate>) =
        coordinates.takeLast(size - mineCount).map { Pair(it, Block()) }

    private fun createCoordinates() = (0 until height).flatMap { x ->
        (0 until width).map { y -> Coordinate(x, y) }
    }

    private fun notifySetMine(mine: Coordinate) {
        for (coordinate in mine.findNotifyRange(width, height)) {
            _boardInfo[coordinate]?.increaseMineCount()
        }
    }

    companion object {
        private const val MINE_MIN = 1

        fun isValidMineCount(width: Int, height: Int, mineCount: Int) = mineCount in MINE_MIN..(width * height)

        fun getOrNull(width: Int, height: Int, mineCount: Int): Board? {
            if (isValidMineCount(width, height, mineCount)) {
                return Board(width, height, mineCount)
            }
            return null
        }
    }
}
