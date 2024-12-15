package domain

import RandomMinePositionSetter

class MineSweeperGame(
    val mineSweeperMap: MineSweeperMap,
    val mineCount: Int,
) {
    init {
        require(mineCount >= 0) { "지뢰는 음수개 일수 없음" }
    }

    fun setMinePosition(minePositionSetter: MinePositionSetter = RandomMinePositionSetter()) {
        minePositionSetter.setPosition(mineSweeperMap)
    }

    fun open(
        x: Int,
        y: Int,
    ) {
        mineSweeperMap.open(x, y)
    }

    fun isWin(): Boolean {
        return mineSweeperMap.blocks.blocks.stream().allMatch { it.isMine || it.isOpen }
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
