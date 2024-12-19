package minesweeper.view

import minesweeper.controller.Land
import minesweeper.domain.spot.Spot

object ResultView {
    fun showLand(land: Land) {
        println("지뢰찾기 게임 시작")
        land.spots.forEach { lines ->
            println(lines.toShowString())
        }
    }
}

private fun Array<Spot>.toShowString() =
    this.joinToString(
        separator = " ",
        transform = { it.toString() },
    )
