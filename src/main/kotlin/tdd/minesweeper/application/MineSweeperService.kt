package tdd.minesweeper.application

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.MineBoard
import tdd.minesweeper.domain.Point
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.provider.RowsProvider
import tdd.minesweeper.repository.MineBoardRepository
import tdd.minesweeper.ui.request.MineBoardCreateRequest

class MineSweeperService(
    private val mineBoardRepository: MineBoardRepository,
    private val rowsProvider: RowsProvider
) {

    fun findMineBoard(findId: Int): MineBoard = mineBoardRepository.find(findId)

    fun createMineBoard(request: MineBoardCreateRequest): Pair<Int, GameProgressStatus> {
        val area = Area(width = request.width, height = request.height)
        val rows = rowsProvider.provide(
            area = area,
            mineCapacity = request.mineCapacity
        )

        return mineBoardRepository.save(
            entity = MineBoard(
                area = area,
                rows = rows
            )
        ) to GameProgressStatus.CREATED
    }

    fun markPoint(id: Int, point: Point): Pair<MineBoard, GameProgressStatus> {
        val foundBoard = mineBoardRepository.find(id)

        val progressStatus = foundBoard.marking(point)
        return foundBoard to progressStatus
    }
}
