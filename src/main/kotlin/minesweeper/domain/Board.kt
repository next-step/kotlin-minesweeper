package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
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
            for (i in 0 until 4) {
                val nx = nowPosition.x + DX[i]
                val ny = nowPosition.y + DY[i]
                if (nx < Height.DEFAULT_HEIGHT - 1 || ny < Width.DEFAULT_WIDTH - 1 || isMineBlock(Position(nx, ny))) {
                    continue
                }
                if (bfsBlocks.find { it.position == Position(nx, ny) } == null) {
                    continue
                }
                if (!bfsBlocks.first { it.position == Position(nx, ny) }.isOpened()) {
                    queue.offer(Position(nx, ny))
                }
            }
        }
        return Board(bfsBlocks.toList())
    }

    private fun isMineBlock(position: Position): Boolean {
        if (blocks.find { it.position == position } == null) {
            return false
        }
        return blocks.first { it.position == position }.isMine
    }

    companion object {
        private val DX = listOf(0, 0, 1, -1)
        private val DY = listOf(1, -1, 0, 0)

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
