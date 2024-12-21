package minesweeper.view

import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

fun Spot.characterOf(): CharSequence {
    return when(this) {
        is MineSpot -> "*"
        else -> "C"
    }
}
