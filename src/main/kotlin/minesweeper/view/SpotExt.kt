package minesweeper.view

import minesweeper.domain.spot.DefaultSpot
import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

fun Spot.characterOf(): CharSequence {
    return when (this) {
        is MineSpot -> "*"
        is DefaultSpot -> if (this.surroundMineCount > 0) this.surroundMineCount.toString() else "C"
        else -> "C"
    }
}
