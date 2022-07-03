package domain

/**
 * 지뢰찾기 판을 만드는 클래스.
 * Created by Jaesungchi on 2022.06.28..
 */
class BoardMaker(private val gameSettingInfo: GameSettingInfo) {
    fun makeBoard(): Board {
        val board = Board(createGround())
        setMineCount(board)
        return board
    }

    private fun createGround(): Map<Position, Ground> {
        val grounds: MutableList<Ground> = mutableListOf()
        repeat(gameSettingInfo.mineCount) {
            grounds.add(Ground(isMine = true))
        }

        val nonMineCount = gameSettingInfo.height * gameSettingInfo.width - gameSettingInfo.mineCount
        repeat(nonMineCount) {
            grounds.add(Ground(isMine = false))
        }

        val map: MutableMap<Position, Ground> = mutableMapOf()
        val positions: List<Position> = (START_INDEX until gameSettingInfo.height).flatMap { height ->
            (START_INDEX until gameSettingInfo.width).map { width ->
                Position(width, height)
            }
        }
        grounds.shuffled().forEachIndexed { index, ground ->
            map[positions[index]] = ground
        }
        return map
    }

    private fun setMineCount(board: Board) {
        val minePositions = board.grounds.filter { it.value.isMine }.map { it.key }
        minePositions.forEach {
            setMineCount(it, board)
        }
    }

    private fun setMineCount(position: Position, board: Board) {
        repeat(ARROW_COUNT) {
            val tempX = position.x + X_MARGINS[it]
            val tempY = position.y + Y_MARGINS[it]
            val position = Position.makePositionOrNull(tempX, tempY, gameSettingInfo.width, gameSettingInfo.height)
                ?: return@repeat
            board.grounds[position]?.addMineCount()
        }
    }

    companion object {
        private const val START_INDEX = 0
        const val ARROW_COUNT = 8
        val Y_MARGINS = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val X_MARGINS = listOf(-1, 0, 1, -1, 1, -1, 0, 1)
    }
}
