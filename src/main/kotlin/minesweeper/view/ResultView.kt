package minesweeper.view

import minesweeper.controller.Land
import minesweeper.domain.spot.Spot

object ResultView {
    fun showLand(land: Land) {
        println("지뢰찾기 게임 시작")
        val height = land.height
        for (n in 0..<height) {
            println(land.getLines(n).toShowString())
        }
    }
}

private fun Array<Spot>.toShowString() =
    this.joinToString(
        separator = " ",
        transform = { it.displayCharacter() },
    )
