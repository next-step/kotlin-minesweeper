package domain

class MineSweeperGame(
    val mineSweeperMap: MineSweeperMap,
    val mineCount: Int,
    val minePositionSetter: MinePositionSetter = RandomMinePositionSetter(),
) {
    fun setMinePosition() {
        minePositionSetter.setPosition(mineSweeperMap)
    }

    companion object {
        fun of(
            width: Int,
            height: Int,
            mineCount: Int,
            minePositionSetter: MinePositionSetter = RandomMinePositionSetter(),
        ): MineSweeperGame {
            val map = MineSweeperMap.getDefaultMap(width, height)
            repeat(mineCount) {
                minePositionSetter.setPosition(map)
            }
            return MineSweeperGame(map, mineCount)
        }
    }
}
