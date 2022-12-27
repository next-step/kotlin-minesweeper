package minesweeper.domain

class MineCount(
    val count: Int,
    private val boardSize: Int,
    minePositionList: List<Int> = emptyList()
) {

    val minePositionList: List<Int> = makeMinePositionList(minePositionList)

    init {
        require(count > 0) { "지뢰는 1개 이상이어야 합니다" }
        require(count <= boardSize) { "지뢰 개수는 보드 크기보다 많으면 안됩니다" }
    }

    private fun makeMinePositionList(minePositionList: List<Int>): List<Int> {
        if (minePositionList.isNotEmpty()) return minePositionList
        return (0 until boardSize).shuffled().take(count)
    }
}
