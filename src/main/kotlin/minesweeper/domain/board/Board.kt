package minesweeper.domain.board

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy
import minesweeper.domain.board.state.GameState
import minesweeper.domain.board.state.Lose
import minesweeper.domain.board.state.Running
import minesweeper.domain.board.state.Win
import java.util.LinkedList
import java.util.Queue

data class Board(val blocks: List<Block>, val gameState: GameState = Running) {

    fun isFinish(): Boolean = gameState.isFinished

    fun open(position: Position): Board {
        if (blocks.isMinePosition(position)) {
            return Board(blocks, Lose)
        }
        return openBlocks(position)
    }

    private fun openBlocks(position: Position): Board {
        var openedBlocks = blocks.toMutableList()
        val queue: Queue<Position> = LinkedList(listOf(position))
        while (queue.isNotEmpty()) {
            val nowPosition = queue.poll()
            val nowIndex = openedBlocks.indexToPosition(nowPosition)
            openedBlocks[nowIndex] = openedBlocks[nowIndex].open()
            val nowBlock = openedBlocks[nowIndex]
            if (!nowBlock.isMine && nowBlock.adjacentMineCount(this).isEmpty()) {
                OpenDirections.values()
                    .map { nextPosition(it, nowPosition) }
                    .filter { isOpenable(it, openedBlocks) }
                    .forEach { queue.offer(it) }
            }
        }
        return calculateGameState(openedBlocks)
    }

    private fun nextPosition(openDirection: OpenDirections, nowPosition: Position): Position {
        val nextCoordinate = openDirection.nextCoordinate(nowPosition.x, nowPosition.y)
        return Position(nextCoordinate.first, nextCoordinate.second)
    }

    private fun isOpenable(position: Position, openedBlocks: List<Block>): Boolean {
        if (openedBlocks.notContainsPosition(position) || openedBlocks.isMinePosition(position)) {
            return false
        }
        return !openedBlocks.findByPosition(position).isOpened()
    }

    private fun calculateGameState(openedBlocks: MutableList<Block>): Board {
        if (openedBlocks.isEmptyBlocksAllOpened()) {
            return Board(openedBlocks.toList(), Win)
        }
        return Board(openedBlocks.toList())
    }

    private fun List<Block>.isMinePosition(position: Position): Boolean = findByPosition(position).isMine

    private fun List<Block>.findByPosition(position: Position): Block = first { it.position == position }

    private fun List<Block>.indexToPosition(position: Position): Int = map { it.position }.indexOf(position)

    private fun List<Block>.notContainsPosition(position: Position): Boolean = position !in map { it.position }

    private fun List<Block>.isEmptyBlocksAllOpened(): Boolean = filter { !it.isMine }.all { it.isOpened() }

    companion object {
        private const val START = 1

        fun of(area: Area, mineCount: MineCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minePositions = mineBlockGenerateStrategy.generate(positions, mineCount.mineCount)
            return Board(positions.map { Block.minesOrCell(it, minePositions) })
        }

        private fun positions(width: Int, height: Int): List<Position> =
            (START..width).flatMap { x ->
                (START..height).map { y ->
                    Position(x, y)
                }
            }
    }
}
