package domain

class BoardGenerator private constructor(
    private val width: Int,
    private val height: Int,
    private val mineCount: Int = 0
) {
    private val boardMap = LinkedHashMap<Location, Block>(width * height)

    init {
        initBoard()
    }

    fun execute(): Board {
        boardMap.clear()
        initBoard()
        return Board(boardMap.toMap(), mineCount)
    }

    private fun initBoard() {
        val locations = createLocations().shuffled()
        val mines = createMines(locations)
        boardMap.putAll((mines + createGenerals(locations)).sortedBy { it.first })
        mines.forEach { notifySetMine(boardMap, it.first) }
    }

    private fun notifySetMine(board: LinkedHashMap<Location, Block>, mine: Location) {
        for (location in Direction.findSurroundings(mine)) {
            board[location]?.increaseMineCount()
        }
    }

    private fun createMines(locations: List<Location>) =
        locations.take(mineCount).map { Pair(it, Mine()) }

    private fun createGenerals(locations: List<Location>) =
        locations.takeLast(width * height - mineCount).map { Pair(it, NormalBlock()) }

    private fun createLocations() = (0 until width).flatMap { x ->
        (0 until height).map { y -> Location(x, y) }
    }

    companion object {
        private const val MINE_MIN = 1

        private fun isValidMineCount(width: Int, height: Int, mineCount: Int) = mineCount in MINE_MIN..(width * height)

        fun getOrNull(width: Int, height: Int, mineCount: Int): BoardGenerator? {
            if (isValidMineCount(width, height, mineCount)) {
                return BoardGenerator(width, height, mineCount)
            }
            return null
        }
    }
}
