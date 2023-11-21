package minesweeper.domain

object MineSweeperValidator {

    fun validate(mineSweeperIndex: MineSweeperIndex, mineSweeperIndexes: MineSweeperIndexes): MineSweeperIndex {
        val mineSweeperIndexesPosition = mineSweeperIndexes.mineSweeperIndexes.map { it.position }
        val indexPosition = mineSweeperIndex.position
        require(mineSweeperIndexesPosition.contains(indexPosition)) { MINE_SCOPE_ERROR_MESSAGE }
        return mineSweeperIndex
    }

    private const val MINE_SCOPE_ERROR_MESSAGE = "지뢰 찾기 범위를 벗어났습니다."
}
