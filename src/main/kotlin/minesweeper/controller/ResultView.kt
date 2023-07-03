package minesweeper.controller

import minesweeper.domain.Block
import minesweeper.domain.Empty
import minesweeper.domain.Mine

object ResultView {

    fun writeMinesweeper(blocks: List<List<Block>>) {
        println("지뢰찾기 게임 시작")
        blocks.forEach {
            println(row(it))
        }
    }

    private fun row(row: List<Block>): String {
        return row.joinToString(" ") {
            when (it) {
                Empty -> "C"
                Mine -> "*"
            }
        }
    }
}
