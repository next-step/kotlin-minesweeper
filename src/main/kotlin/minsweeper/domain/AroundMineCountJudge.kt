package minsweeper.domain

class AroundMineCountJudge {

    fun judge(
        boardSize: BoardSize,
        position: Position,
        minePositions: List<Position>,
    ): Int = position.findAroundPositions(boardSize)
        .matches(minePositions)

    private fun Position.findAroundPositions(boardSize: BoardSize): List<Position> = listOfNotNull(
        left(),
        right(boardSize.width),
        topLeft(),
        topCenter(),
        topRight(boardSize.width),
        bottomLeft(boardSize.height),
        bottomCenter(boardSize.height),
        bottomRight(boardSize.width, boardSize.height),
    )

    private fun List<Position>.matches(minePositions: List<Position>): Int = this.intersect(minePositions.toSet()).size

}
