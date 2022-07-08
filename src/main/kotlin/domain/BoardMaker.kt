package domain

/**
 * 지뢰찾기 판을 만드는 클래스.
 * Created by Jaesungchi on 2022.06.28..
 */
class BoardMaker(private val gameSettingInfo: GameSettingInfo) {
    fun makeBoard(manualMines: List<Position>? = null): Board {
        val board = Board(createGround(), gameSettingInfo)
        board.setManualMine(manualMines)
        return board
    }

    private fun createGround(): Map<Position, Ground> {
        val positions: List<Position> = (START_INDEX until gameSettingInfo.height).flatMap { height ->
            (START_INDEX until gameSettingInfo.width).map { width ->
                Position(width, height)
            }
        }
        return positions.associateWith { Ground() }
    }

    companion object {
        private const val START_INDEX = 0
    }
}
