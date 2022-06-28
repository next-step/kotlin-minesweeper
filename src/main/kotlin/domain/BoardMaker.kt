package domain

/**
 * 지뢰찾기 판을 만드는 클래스.
 * Created by Jaesungchi on 2022.06.28..
 */
class BoardMaker(private val gameSettingInfo: GameSettingInfo) {
    fun makeBoard(): Board {
        return Board(createGround())
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

    companion object {
        private const val START_INDEX = 0
    }
}
