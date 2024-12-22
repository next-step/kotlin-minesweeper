package minsweeper.domain.generate

import minsweeper.domain.judge.AroundMineAmountJudge
import minsweeper.domain.board.BoardSize
import minsweeper.domain.Cell
import minsweeper.domain.Coordinate

class CellGenerator(
    private val aroundMineAmountJudge: AroundMineAmountJudge = AroundMineAmountJudge(),
) {

    fun generate(
        coordinate: Coordinate,
        mineCoordinates: List<Coordinate>,
        boardSize: BoardSize,
    ): Cell {
        if (coordinate in mineCoordinates) {
            return Cell.Mine()
        }

        val aroundMineAmount = aroundMineAmountJudge.judge(coordinate, mineCoordinates, boardSize)
        return Cell.Island(aroundMineAmount = aroundMineAmount)
    }

}