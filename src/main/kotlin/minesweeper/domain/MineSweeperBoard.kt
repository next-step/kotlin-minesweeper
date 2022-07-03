package minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Map<Position, Zone>,
)
