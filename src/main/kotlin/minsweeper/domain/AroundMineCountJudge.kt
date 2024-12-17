package minsweeper.domain

class AroundMineCountJudge {

    fun judge(
        boardSize: BoardSize,
        coordinate: Coordinate,
        mineCoordinates: List<Coordinate>,
    ): Int = coordinate.findAroundCoordinates(boardSize)
        .matches(mineCoordinates)

    private fun Coordinate.findAroundCoordinates(boardSize: BoardSize): List<Coordinate> = listOfNotNull(
        left(),
        right(boardSize.width),
        topLeft(),
        topCenter(),
        topRight(boardSize.width),
        bottomLeft(boardSize.height),
        bottomCenter(boardSize.height),
        bottomRight(boardSize.width, boardSize.height),
    )

    private fun List<Coordinate>.matches(mineCoordinates: List<Coordinate>): Int = this.intersect(mineCoordinates.toSet()).size

}
