package view

import domain.model.GameMap
import domain.model.Mine
import domain.model.NumberTile
import domain.model.Tile

object OutputView {
    private const val MINE = "*"
    private const val UNOPENED = "C"

    fun printGameStart(map: GameMap) {
        println("지뢰찾기 게임 시작")

        map.field.forEach { printColumn(it) }
    }

    fun printMessage(message: String): Unit = println(message)

    private fun printColumn(column: List<Tile>) {
        column.forEach { printTile(it) }
        println()
    }

    private fun printTile(tile: Tile) {
        if (!tile.isOpened) {
            print("$UNOPENED ")
            return
        }

        val output = when (tile) {
            is Mine -> MINE
            is NumberTile -> tile.value
        }
        print("$output ")
    }
}
