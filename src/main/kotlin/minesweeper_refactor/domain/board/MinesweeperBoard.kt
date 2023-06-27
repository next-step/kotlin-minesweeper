package minesweeper_refactor.domain.board

import minesweeper_refactor.domain.block.Block
import minesweeper_refactor.domain.block.Blocks
import minesweeper_refactor.domain.block.OpenState
import minesweeper_refactor.domain.coordinate.Coordinate
import minesweeper_refactor.domain.coordinate.FourDirectionsDecision

class MinesweeperBoard(numberBlocks: Blocks, mineBlocks: Blocks) {

    private val remainNumberBlocks: MutableSet<Block> = numberBlocks.toMutableSet()
    private val board: Map<Coordinate, Block> = (mineBlocks + numberBlocks).toMinesweeperBoard()

    fun sortedBlocks(): List<Block> = board.values.sortedBy { it.coordinate }

    fun isWinGame(): Boolean = remainNumberBlocks.isEmpty()

    fun openBlock(coordinate: Coordinate): OpenState {
        val board = board[coordinate]
            ?: throw IllegalStateException("보드 범위에 존재하는 블록을 열어야 합니다. 입력 좌표 : $coordinate")

        remainNumberBlocks.remove(element = board)

        return board.open()
    }

    tailrec fun openAroundBlock(coordinates: List<Coordinate>) {
        val needToOpenAroundBlocks = coordinates.mapNotNull { board[it] }
            .filter { it.open().isOpenAroundBlocks() }

        remainNumberBlocks.removeAll(elements = needToOpenAroundBlocks.toSet())

        if (needToOpenAroundBlocks.isEmpty()) {
            return
        }

        val aroundBlocks = needToOpenAroundBlocks.flatMap {
            it.coordinate.toAroundDirections(aroundDecision = FourDirectionsDecision)
        }

        openAroundBlock(coordinates = aroundBlocks)
    }
}
