package minesweeper.controller

import minesweeper.domain.MineBoard
import minesweeper.infra.io.MineSweeperReader
import minesweeper.view.MineBoardView
import minesweeper.view.MineSweeperView

class MineSweeperController(
    private val reader: MineSweeperReader = MineSweeperReader(),
    private val mineSweeperView: MineSweeperView = MineSweeperView(),
    private val mineBoardView: MineBoardView = MineBoardView()
) {
    fun run() {
        mineSweeperView.printlnHeightInputPrompt()
        val height = reader.readInt()

        mineSweeperView.printlnWidthInputPrompt()
        val width = reader.readInt()

        mineSweeperView.printlnTotalMineCountInputPrompt()
        val totalMineCount = reader.readInt()

        val mineBoard = MineBoard(height, width, totalMineCount)

        mineSweeperView.printlnMineSweeperBeforeStart()
        mineBoardView.printlnMineBoard(mineBoard)
    }
}
