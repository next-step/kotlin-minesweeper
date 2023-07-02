package domain

import domain.math.PositiveNumber

data class MineSweeperInitProperty(
    val height: PositiveNumber,
    val width: PositiveNumber,
    val mineCount: PositiveNumber,
) {

    init {
        require(mineCount < (width * height)) {
            "count of mines must be at least less than count of cells."
        }
    }
}
