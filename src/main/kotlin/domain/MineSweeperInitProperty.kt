package domain

import domain.math.PositiveNumber

data class MineSweeperInitProperty(
    val height: PositiveNumber,
    val width: PositiveNumber,
    val mineCount: PositiveNumber,
)
