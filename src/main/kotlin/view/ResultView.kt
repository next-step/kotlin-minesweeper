package view

import domain.MineBoard

object ResultView {
    fun printBoard(mineBoard: MineBoard) {
        mineBoard.rows
            .map { row ->
                row.tiles.value.joinToString(" ") { it.alias }
            }
            .forEach {
                println(it)
            }
    }
}
