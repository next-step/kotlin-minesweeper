package minesweeper.domain.board

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Blocks
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy
import minesweeper.domain.board.state.GameState
import minesweeper.domain.board.state.Lose
import minesweeper.domain.board.state.Running
import minesweeper.domain.board.state.Win
import java.util.LinkedList
import java.util.Queue

data class Board(val blocks: Blocks, val gameState: GameState = Running) {

    fun isFinish(): Boolean = gameState.isFinished

    fun open(position: Position): Board {
        if (blocks.isMinePosition(position)) {
            return Board(blocks, Lose)
        }
        return openBlocks(position)
    }

    private fun openBlocks(position: Position): Board {
        val queue: Queue<Position> = LinkedList(listOf(position))
        var blocks = blocks.copy()
        while (queue.isNotEmpty()) {
            val nowPosition = queue.poll()
            blocks = blocks.open(nowPosition)
            if (!blocks.isMinePosition(nowPosition) && blocks.adjacentMineCount(nowPosition).isEmpty()) {
                OpenDirections.values()
                    .map { nextPosition(it, nowPosition) }
                    .filter { isOpenable(it, blocks) }
                    .forEach { queue.offer(it) }
            }
        }
        return calculateGameState(blocks)
    }

    private fun nextPosition(openDirection: OpenDirections, nowPosition: Position): Position {
        val nextCoordinate = openDirection.nextCoordinate(nowPosition.x, nowPosition.y)
        return Position(nextCoordinate.first, nextCoordinate.second)
    }

    private fun isOpenable(position: Position, blocks: Blocks): Boolean {
        if (!blocks.containsPosition(position) || blocks.isMinePosition(position)) {
            return false
        }
        return !blocks.findBlockByPosition(position).isOpened()
    }

    private fun calculateGameState(blocks: Blocks): Board {
        if (blocks.isEmptyBlocksAllOpened()) {
            return Board(blocks, Win)
        }
        return Board(blocks)
    }

    companion object {
        private const val START = 1

        fun of(area: Area, mineCount: MineCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minePositions = mineBlockGenerateStrategy.generate(positions, mineCount.mineCount)
            return Board(Blocks(positions.map { Block.create(it, minePositions) }))
        }

        private fun positions(width: Int, height: Int): List<Position> =
            (START..width).flatMap { x ->
                (START..height).map { y ->
                    Position(x, y)
                }
            }
    }
}
