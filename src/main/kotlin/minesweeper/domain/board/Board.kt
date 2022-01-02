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

data class Board(val blocks: Blocks, val gameState: GameState = Running) {

    fun isFinish(): Boolean = gameState.isFinished

    fun open(position: Position): Board {
        if (blocks.isMinePosition(position)) {
            return Board(blocks, Lose)
        }
        return openBlocks(position)
    }

    private fun openBlocks(position: Position): Board {
        val openedBlocks = blocks.open(position)
        return calculateGameState(openedBlocks)
    }

    private fun calculateGameState(blocks: Blocks): Board {
        if (blocks.isEmptyBlocksAllOpened()) {
            return Board(blocks, Win)
        }
        return Board(blocks)
    }

    fun findBlock(position: Position): Block = blocks.findBlock(position)

    companion object {
        private const val START = 1

        fun of(area: Area, mineCount: MineCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minePositions = mineBlockGenerateStrategy.generate(positions, mineCount.mineCount)
            return Board(Blocks(positions.associateWith { Block.create(it in minePositions) }.toMutableMap()))
        }

        private fun positions(width: Int, height: Int): List<Position> =
            (START..width).flatMap { x ->
                (START..height).map { y ->
                    Position(x, y)
                }
            }
    }
}
