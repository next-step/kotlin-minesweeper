package minesweeper_refactor.convert

import minesweeper_refactor.domain.block.Block
import minesweeper_refactor.domain.board.MatchState
import minesweeper_refactor.domain.board.MinesweeperBoard
import minesweeper_refactor.view.model.BlockRowsView
import minesweeper_refactor.view.model.BlockStateView
import minesweeper_refactor.view.model.BoardView
import minesweeper_refactor.view.model.MatchStateView

fun MinesweeperBoard.convertToView(): BoardView = this.sortedBlocks()
    .groupBy { it.coordinate.y }
    .mapValues { it.value.convertToView() }
    .run(::BoardView)

fun List<Block>.convertToView(): BlockRowsView = this.map { it.convertToView() }
    .run(::BlockRowsView)

fun Block.convertToView(): BlockStateView = if (isHidden) {
    BlockStateView.HIDDEN
} else {
    BlockStateView.valueOf(value = blockState.name)
}

fun MatchState.convertToView(): MatchStateView = MatchStateView.valueOf(value = this.name)
