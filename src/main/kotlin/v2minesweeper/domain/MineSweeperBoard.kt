package v2minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Map<Pair<Int, Int>, Zone>
)
