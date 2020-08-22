package domain

class Board private constructor(
    val width: Int,
    private val height: Int,
    private val mineCount: Int = 0
) {
    val boardInfo: Map<Location, Block> get() = _boardInfo.toMap()

    private var remainBlock = width * height
    private val _boardInfo = LinkedHashMap<Location, Block>(remainBlock)

    init {
        initBoard()
    }

    fun open(location: Location): Result {
        val selectedBlock = boardInfo[location] ?: return Result.INVALID
        if (selectedBlock.isOpened) {
            return Result.ALREADY_OPEN
        }

        remainBlock -= selectedBlock.open()
        if (selectedBlock.mineCount == 0) {
            openSurroundings(location)
        }

        return when {
            selectedBlock.isMine() -> Result.LOSE
            isWin() -> Result.WIN
            else -> Result.PROGRESS
        }
    }

    fun openAll() {
        boardInfo.entries.forEach { it.value.open() }
        remainBlock = 0
    }

    private fun isWin() = remainBlock == mineCount

    private fun initBoard() {
        val locations = createLocations().shuffled()
        val mines = createMines(locations)
        _boardInfo.putAll((mines + createGenerals(locations)).sortedBy { it.first })
        mines.forEach { notifySetMine(it.first) }
    }

    private fun createMines(locations: List<Location>) =
        locations.take(mineCount).map { Pair(it, Block(BlockType.MINE)) }

    private fun createGenerals(locations: List<Location>) =
        locations.takeLast(remainBlock - mineCount).map { Pair(it, Block()) }

    private fun createLocations() = (0 until height).flatMap { x ->
        (0 until width).map { y -> Location(x, y) }
    }

    private fun notifySetMine(mine: Location) {
        for (location in findSurroundings(mine)) {
            _boardInfo[location]?.increaseMineCount()
        }
    }

    private fun openSurroundings(mine: Location) =
        findSurroundings(mine).filterNot { boardInfo[it]?.isOpened ?: true }.forEach { open(it) }

    private fun findSurroundings(mine: Location) =
        Direction.values().map { mine + it }.filter { mine in this }

    operator fun contains(location: Location) = _boardInfo.contains(location)

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
