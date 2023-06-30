package tdd.minesweeper.ui

import tdd.minesweeper.application.MineSweeperService
import tdd.minesweeper.domain.Point
import tdd.minesweeper.ui.request.MineBoardCreateRequest
import tdd.minesweeper.ui.response.MineBoardResponse

class MineSweeperController(
    private val mineSweeperService: MineSweeperService
) {
    fun createMineBoard(request: MineBoardCreateRequest): MineBoardResponse {
        val (savedId, status) = mineSweeperService.createMineBoard(request)

        return mineSweeperService.findMineBoard(savedId).let {
            MineBoardResponse.of(entity = it, status = status)
        }
    }

    fun markBoard(id: Int, point: Point): MineBoardResponse {
        val (mineBoard, status) = mineSweeperService.markPoint(id = id, point = point)

        return MineBoardResponse.of(entity = mineBoard, status = status)
    }
}
