package domain

class Board private constructor(
    val width: Int,
    private val height: Int,
    private val mineCount: Int = 0
) {
    val boardInfo: Map<Location, Block> get() = _boardInfo.toMap()

    private val size = width * height
    private val _boardInfo = LinkedHashMap<Location, Block>(size)

    init {
        initBoard()
    }

    fun open(location: Location) {
        boardInfo[location]?.open()
    }

    private fun initBoard() {
        val locations = createLocations().shuffled()
        val mines = createMines(locations)
        val points = (mines + createGenerals(locations)).sortedBy { it.first }

        _boardInfo.putAll(points)
        mines.forEach { notifySetMine(it.first) }
    }

    private fun createMines(locations: List<Location>) =
        locations.take(mineCount).map { Pair(it, Block(BlockType.MINE)) }

    private fun createGenerals(locations: List<Location>) =
        locations.takeLast(size - mineCount).map { Pair(it, Block()) }

    private fun createLocations() = (0 until height).flatMap { x ->
        (0 until width).map { y -> Location(x, y) }
    }

    private fun notifySetMine(mine: Location) {
        for (location in mine.findNotifyRange(width, height)) {
            _boardInfo[location]?.increaseMineCount()
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
