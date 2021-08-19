package minesweeper.controller

import minesweeper.domain.Ground
import minesweeper.domain.MineGenerator.generateMinePositions
import minesweeper.domain.Position
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

    val positions = generateMinePositions(countOfMine, ground)

    ResultView.printToStartGame()

    val markersWithMine = generateGroundWithMine(generateDefaultGround(height, vertical), positions)

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

private fun generateGroundWithMine(markers: MutableList<List<String>>, positions: HashSet<Position>): MutableList<List<String>> {
    val markersWithMine = markers.toMutableList()

    positions.forEach {
        val x = it.x
        val xOfMarkers = markers[x].toMutableList()
        xOfMarkers[it.y] = "* "

        markersWithMine[x] = xOfMarkers
    }

    return markersWithMine
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