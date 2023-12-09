package minesweeper

import minesweeper.board.BoardDimensions
import minesweeper.position.Position

class MinesweeperBoard(
    private val admin: AdminBoard,
    private val player: PlayerBoard,
    private val boardDimensions: BoardDimensions
) {
    private val positionsQueue: ArrayDeque<Position> = ArrayDeque()

    fun openCell(position: Position): CellOpenStatus {
        if (admin.isMinePosition(position)) {
            return CellOpenStatus.FAIL
        }

        positionsQueue.add(position)

        while(!positionsQueue.isEmpty()) {
            val now = positionsQueue.removeFirst()
            player.setCell(now, admin.getCell(now))
            player.visit(now)
            addAndSetPositions(now.nearPositions(boardDimensions))
        }
        return CellOpenStatus.SUCCESS
    }

    private fun addAndSetPositions(positions: List<Position>) {
        positions.forEach {
            if(!admin.isMinePosition(it)) {
                player.setCell(it, admin.getCell(it))
            }
            if (admin.isCleanCell(it) && !player.isVisited(it)) {
                positionsQueue.add(it)
            }
        }
    }

    fun playerBoardRender() = player.boardRender()

    companion object {
        private const val INIT_CELL = 'C'
    }
}
