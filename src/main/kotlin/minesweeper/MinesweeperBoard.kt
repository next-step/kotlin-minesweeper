package minesweeper

import minesweeper.board.BoardDimensions
import minesweeper.position.Position

class MinesweeperBoard(
    private val admin: AdminBoard,
    private val player: PlayerBoard,
    private val boardDimensions: BoardDimensions
) {
    private val positionsQueue: ArrayDeque<Position> = ArrayDeque()

    fun openCell(position: Position): PlayStatus {
        if (admin.isMinePosition(position)) {
            return PlayStatus.LOSE
        } else if (player.isVisited(position)) {
            return PlayStatus.OPEN
        }

        positionsQueue.add(position)
        player.findCell()
        player.visit(position)

        while (!positionsQueue.isEmpty()) {
            val now = positionsQueue.removeFirst()
            player.setCell(now, admin.getCell(now))
            if (admin.hasNotMineAround(now)) {
                addAndSetPositions(now.nearPositions(boardDimensions))
            }
        }

        return if (player.isFindAllCell()) PlayStatus.WIN
        else PlayStatus.OPEN
    }

    private fun addAndSetPositions(positions: List<Position>) {
        positions.forEach {
            if (!player.isVisited(it)) {
                player.visit(it)
                player.findCell()
                player.setCell(it, admin.getCell(it))
                if (admin.hasNotMineAround(it)) {
                    positionsQueue.add(it)
                }
            }
        }
    }

    fun playerBoardRender() = player.boardRender()

    companion object {
        private const val INIT_CELL = 'C'
    }
}
