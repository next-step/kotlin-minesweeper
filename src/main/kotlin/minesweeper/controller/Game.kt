package minesweeper.controller

import minesweeper.domain.*
import minesweeper.view.InputView
import minesweeper.view.ResultView


fun main() {

    ResultView.printToReceiptHeight()
    val height = InputView.receiptInt()

    ResultView.printToReceiptVertical()
    val vertical = InputView.receiptInt()

    val ground = Ground(height, vertical)

    ResultView.printToReceiptCountOfMine()
    val countOfMine = InputView.receiptInt()

    ResultView.printToStartGame()

    val mineGenerator = MineGenerator(ground)
    val positions = Positions(mineGenerator.generateMinePositions(RandomPositionGenerator(), countOfMine))
    val markersWithMine = positions.generateGroundWithMine(generateDefaultGround(height, vertical))

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

private fun generateDefaultGround(height: Int, vertical: Int): MutableList<List<String>> {
    val markers = mutableListOf<List<String>>()

    (0 until height).map {
        val marker = mutableListOf<String>()
        (0 until vertical).map {
            marker.add("C ")
        }

        markers.add(marker)
    }

    return markers
}