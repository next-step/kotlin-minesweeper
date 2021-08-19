package minesweeper.controller

import minesweeper.domain.*
import minesweeper.view.InputView
import minesweeper.view.ResultView


fun main() {

    ResultView.printToReceiptHeight()
    val height = InputView.receiptInt()

    ResultView.printToReceiptVertical()
    val vertical = InputView.receiptInt()

    val marker = Marker(height, vertical)

    ResultView.printToReceiptCountOfMine()
    val countOfMine = InputView.receiptInt()

    ResultView.printToStartGame()

    val mineGenerator = MineGenerator(marker)
    val positions = Positions(mineGenerator.generateMinePositions(RandomPositionGenerator(), countOfMine))
    val markersWithMine = positions.generateGroundWithMine(marker.generateAllGround())

    printAllMineGround(markersWithMine)
}

private fun printAllMineGround(markersWithMine: MutableList<List<String>>) {
    (markersWithMine.indices).map { x ->
        printMineGround(markersWithMine, x)
        println()
    }
}

private fun printMineGround(markersWithMine: MutableList<List<String>>, x: Int) {
    (markersWithMine[0].indices).map { y ->
        print(markersWithMine[x][y])
    }
}