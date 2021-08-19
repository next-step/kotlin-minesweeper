package minesweeper

import minesweeper.MineGenerator.generateMinePositions

fun main() {

    println("높이를 입력하세요.")
    val height = readLine()!!.toInt()

    println("높이를 입력하세요.")
    val vertical = readLine()!!.toInt()

    val ground = Ground(height, vertical)

    println("지뢰는 몇 개인가요?")
    val countOfMine = readLine()!!.toInt()

    val positions = generateMinePositions(countOfMine, ground)

    println("지뢰찾기 게임 시작")

    // C 로 가득 채워진 2차 배열을 만든다.
    val markers = mutableListOf<List<String>>()

    (0 until height).map {
        val marker = mutableListOf<String>()
        (0 until vertical).map {
            marker.add("C ")
        }

        markers.add(marker)
    }

    // position 에 해당하는 위치에 * 을 넣는다.
    val markersWithMine = markers.toMutableList()
    positions.forEach {
        val x = it.x
        val xOfMarkers = markers[x].toMutableList()
        xOfMarkers[it.y] = "* "

        markersWithMine[x] = xOfMarkers
    }

    (markersWithMine.indices).map { x ->
        (markersWithMine[0].indices).map { y ->
            print(markersWithMine[x][y])
        }

        println()
    }
}