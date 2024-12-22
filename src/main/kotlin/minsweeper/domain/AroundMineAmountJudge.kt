package minsweeper.domain

class AroundMineAmountJudge {

    fun judge(
        coordinate: Coordinate,
        mineCoordinates: List<Coordinate>,
        boardSize: BoardSize,
    ): Int = coordinate.findAroundCoordinates(boardSize)
        .matches(mineCoordinates)

    private fun Coordinate.findAroundCoordinates(boardSize: BoardSize): List<Coordinate> =
        this.aroundCoordinates(boardSize)

    private fun List<Coordinate>.matches(mineCoordinates: List<Coordinate>): Int =
        this.intersect(mineCoordinates.toSet()).size

}