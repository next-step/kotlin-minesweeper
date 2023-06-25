package minesweeper.convert

import minesweeper.domain.Block
import minesweeper.domain.board.MinesweeperBoard
import minesweeper.view.model.BlockRowsView
import minesweeper.view.model.BoardView

fun MinesweeperBoard.convertToView(): BoardView = this.sortedBlocks()
    .groupBy { it.coordinate.x }
    .mapValues { it.value.convertToView() }
    .run(::BoardView)

fun List<Block>.convertToView(): BlockRowsView = this.map { it.flag.getCurrentState() }
    .run(::BlockRowsView)
