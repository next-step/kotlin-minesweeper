package minesweeper.convert

import minesweeper.domain.Block
import minesweeper.domain.board.MinesweeperBoard
import minesweeper.domain.flag.MatchState
import minesweeper.view.model.BlockRowsView
import minesweeper.view.model.BlockStateView
import minesweeper.view.model.BoardView
import minesweeper.view.model.MatchStateView

fun MinesweeperBoard.convertToView(): BoardView = this.sortedBlocks()
    .groupBy { it.coordinate.x }
    .mapValues { it.value.convertToView() }
    .run(::BoardView)

fun List<Block>.convertToView(): BlockRowsView = this.map { BlockStateView.valueOf(value = it.blockState.name) }
    .run(::BlockRowsView)

fun MatchState.convertToView(): MatchStateView = MatchStateView.valueOf(value = this.name)
