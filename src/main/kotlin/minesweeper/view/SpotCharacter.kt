package minesweeper.view

import minesweeper.domain.spot.DefaultSpot
import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

enum class SpotCharacter(val type: Class<*>, val character: CharSequence) {
    TYPE_MINE(MineSpot::class.java, "*"),
    TYPE_DEFAULT(DefaultSpot::class.java, "C"),
    ;

    companion object {
        fun characterOf(spot: Spot): CharSequence {
            return (SpotCharacter.entries.firstOrNull() {
                it.type == spot::class.java
            }?: TYPE_DEFAULT).character
        }
    }
}