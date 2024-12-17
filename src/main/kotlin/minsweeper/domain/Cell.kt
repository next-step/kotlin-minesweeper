package minsweeper.domain

sealed interface Cell {
    data class Island(val aroundMineCount: Int) : Cell
    data object Mine : Cell

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
