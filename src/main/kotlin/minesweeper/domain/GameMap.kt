package minesweeper.domain

class GameMap(
    val blockTable: BlockTable,
    private var state: GameState = GameState.PLAYING
) {

    fun isFinished(): Boolean {
        return state in listOf(GameState.WIN, GameState.LOSE)
    }

    fun getGameProgress(): GameState {
        return state
    }

    fun open(mapCord: MapCord) {
        if (state != GameState.PLAYING) throw IllegalStateException()

        val newState = blockTable.open(mapCord)
        state = newState
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, mineSettingStrategy: MineSettingStrategy): GameMap {
            val mapCords = MapCords.of(height, width)
            val mineBlockLocations = mineSettingStrategy.getLocations(height * width, mineCount)
            val blocks = Blocks.of(height * width, mineBlockLocations)

            return GameMap(
                BlockTable.of(mapCords, blocks)
            )
        }
    }
}
