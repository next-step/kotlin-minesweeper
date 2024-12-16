package minsweeper.domain

sealed interface Cell {
    data class Island(val aroundMineCount: Int) : Cell
    data object Mine : Cell

    companion object {
        fun create(
            position: Position,
            boardSize: BoardSize,
            minePositions: List<Position>,
            aroundMineCountJudge: AroundMineCountJudge,
        ): Cell = Mine.takeIf { position in minePositions } ?: Island(
            aroundMineCountJudge.judge(
                boardSize,
                position,
                minePositions,
            )
        )
    }
}
