package minesweeper.ui

import minesweeper.domain.Cell
import minesweeper.domain.NumberCell
import minesweeper.domain.enums.CellStatus

object UserInput {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun inputOpenPosition(): Pair<Int, Int> {
        print("open: ")
        val (x, y) = readln().split(",", limit = 2)
            .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException() }
        return Pair(x, y)
    }

    fun printResult(map: List<List<Cell>>) {
        for (y in map.indices) {
            for (x in map[y].indices) {
                print(map[x][y].text())
                    .also { print(" ") }
            }
            println()
        }
    }

    private fun Cell.text(): String {
        if (this is NumberCell && this.status == CellStatus.OPEN) {
            return this.mineCountAround.toString()
        }
        return "C"
    }
}
