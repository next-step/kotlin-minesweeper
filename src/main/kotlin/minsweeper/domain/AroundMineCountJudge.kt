package minsweeper.domain

class AroundMineCountJudge {

    fun judge(
        boardSize: BoardSize,
        coordinate: Coordinate,
        mineCoordinates: List<Coordinate>,
    ): Int = coordinate.findAroundCoordinates(boardSize)
        .matches(mineCoordinates)

    private fun Coordinate.findAroundCoordinates(boardSize: BoardSize): List<Coordinate> =
        this.aroundCoordinates(boardSize)

    private fun List<Coordinate>.matches(mineCoordinates: List<Coordinate>): Int =
        this.intersect(mineCoordinates.toSet()).size

}
