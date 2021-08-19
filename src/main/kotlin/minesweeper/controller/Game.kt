package minesweeper.controller

import minesweeper.domain.Marker
import minesweeper.domain.MineGenerator
import minesweeper.domain.Positions
import minesweeper.domain.RandomPositionGenerator
import minesweeper.view.InputView
import minesweeper.view.Reception
import minesweeper.view.ResultView

fun main() {

    InputView.printToReceiptHeight()
    val height = Reception.receiptInt()

    InputView.printToReceiptVertical()
    val vertical = Reception.receiptInt()

    val marker = Marker(vertical, height)

    InputView.printToReceiptCountOfMine()
    val countOfMine = Reception.receiptInt()

    InputView.printToStartGame()

    val mineGenerator = MineGenerator(marker)
    val positions = Positions(mineGenerator.generateMinePositions(RandomPositionGenerator(), countOfMine))
    val markersWithMine = positions.generateGroundWithMine(marker.generateAllGround())

    ResultView.printAllMineGround(markersWithMine)
}
