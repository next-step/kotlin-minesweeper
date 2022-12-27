package minesweeper.controller

import minesweeper.domain.MineSweeperGame
import minesweeper.domain.position.vendor.PositionsVendor
import minesweeper.infra.io.MineSweeperReader
import minesweeper.util.indexRange
import minesweeper.view.MineBoardView
import minesweeper.view.MineSweeperView

class MineSweeperController(
    private val reader: MineSweeperReader = MineSweeperReader(),
    private val mineSweeperView: MineSweeperView = MineSweeperView(),
    private val mineBoardView: MineBoardView = MineBoardView(),
    private val positionsVendor: PositionsVendor = PositionsVendor()
) {
    fun run() {
        mineSweeperView.printlnHeightInputPrompt()
        val height = reader.readInt()

        mineSweeperView.printlnWidthInputPrompt()
        val width = reader.readInt()

        mineSweeperView.printlnTotalMineCountInputPrompt()
        val totalMineCount = reader.readInt()

        val minePositions = positionsVendor.randomPositions(
            rowIndexRange = height.indexRange(),
            colIndexRange = width.indexRange(),
            totalCount = totalMineCount
        )
        val mineSweeperGame = MineSweeperGame.of(height, width, totalMineCount, minePositions)

        mineSweeperView.printlnMineSweeperBeforeStart()
        mineBoardView.printlnMineBoard(mineSweeperGame)
    }
}
