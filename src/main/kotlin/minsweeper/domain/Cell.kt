package minsweeper.domain

sealed class Cell {
    var isOpened: Boolean = false
        private set

    fun open() {
        isOpened = true
    }

    data class Island(val aroundMineCount: Int) : Cell() {
        fun isAroundMineCountZero() = aroundMineCount == 0
    }
    data object Mine : Cell()

    companion object {
        fun create(
            coordinate: Coordinate,
            boardSize: BoardSize,
            mineCoordinates: List<Coordinate>,
            aroundMineCountJudge: AroundMineCountJudge,
        ): Cell = Mine.takeIf { coordinate in mineCoordinates } ?: Island(
            aroundMineCountJudge.judge(
                boardSize,
                coordinate,
                mineCoordinates,
            )
        )
    }
}
