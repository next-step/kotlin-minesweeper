package domain

class Board(
    val map: Map<Location, Block>,
    private val mineCount: Int
) {
    private var remainBlock = map.size

    init {
        require(mineCount >= 0) { "지뢰 개수는 음수일 수 없습니다." }
    }

    fun open(location: Location): Result {
        val selectedBlock = map[location] ?: return Result.INVALID
        if (selectedBlock.isOpened) {
            return Result.ALREADY_OPEN
        }

        remainBlock -= if (selectedBlock.open()) 1 else 0
        if (selectedBlock.mineCount == 0) {
            openSurroundings(location)
        }

        return when {
            selectedBlock.isMine -> Result.LOSE
            isWin() -> Result.WIN
            else -> Result.PROGRESS
        }
    }

    fun openAll() {
        map.entries.forEach { it.value.open() }
        remainBlock = mineCount
    }

    private fun isWin() = remainBlock == mineCount

    private fun openSurroundings(mine: Location) =
        Direction.findSurroundings(mine)
            .filterNot { map[it]?.isOpened ?: true }
            .forEach { open(it) }
}
