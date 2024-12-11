package domain

class MineSweeperGame(
    val mineSweeperMap: MineSweeperMap,
    val mineCount: Int,
    val minePositionSetter: MinePositionSetter = RandomMinePositionSetter(),
) {
    fun setMinePosition() {
        minePositionSetter.setPosition(mineSweeperMap)
    }
}
