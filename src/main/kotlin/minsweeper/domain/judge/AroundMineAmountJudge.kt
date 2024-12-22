package minsweeper.domain.judge

import minsweeper.domain.Coordinate
import minsweeper.domain.board.BoardSize

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