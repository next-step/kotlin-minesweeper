package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy
import java.util.LinkedList
import java.util.Queue

@JvmInline
value class Board(val blocks: List<Block>) {

    fun openBlock(position: Position): Board? {
        if (isMineBlock(position)) {
            return null
        }
        return calculateBlocks(position)
    }

    private fun calculateBlocks(position: Position): Board {
        var bfsBlocks = blocks.toList()
        val queue: Queue<Position> = LinkedList()
        queue.offer(position)
        while (queue.isNotEmpty()) {
            val nowPosition = queue.poll()
            bfsBlocks = bfsBlocks.map { if (it.position == nowPosition) it.open() else it }
            OpenDirections.values()
                .map { it.nextCoordinate(nowPosition.x, nowPosition.y) }
                .filter { it.first >= Position.DEFAULT_X && it.second >= Position.DEFAULT_Y }
                .map { Position(it.first, it.second) }
                .filter { isOpenable(it, bfsBlocks) }
                .forEach { queue.offer(it) }
        }
        return Board(bfsBlocks.toList())
    }

    private fun isOpenable(position: Position, bfsBlocks: List<Block>): Boolean {
        if (!bfsBlocks.map { it.position }.contains(position)) {
            return false
        }
        if (bfsBlocks.first { it.position == Position(position.x, position.y) }.isMine) {
            return false
        }
        return !bfsBlocks.first { it.position == Position(position.x, position.y) }.isOpened()
    }

    private fun isMineBlock(position: Position): Boolean {
        if (blocks.find { it.position == position } == null) {
            return false
        }
        return blocks.first { it.position == position }.isMine
    }

    companion object {
        private const val START = 0

        fun of(area: Area, mineCount: MineCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minePositions = mineBlockGenerateStrategy.generate(positions, mineCount.mineCount)
            return Board(positions.map { Block.minesOrCell(it, minePositions) })
        }

        private fun positions(width: Int, height: Int): List<Position> =
            (START until width).flatMap { x ->
                (START until height).map { y ->
                    Position(x, y)
                }
            }
    }
}
