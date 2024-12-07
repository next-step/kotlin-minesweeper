package minesweeper

object Minesweeper {
    private const val START_RANGE = 1

    fun setUp(
        height: Int,
        width: Int,
    ): Cells {
        return Cells.generate(START_RANGE..height, START_RANGE..width)
    }

    fun chooseMinePosition(
        height: Int,
        width: Int,
        mine: Int,
    ): Mines {
        val minePositions = List(mine) {
            Cell(
                Height((START_RANGE..height).random()),
                Width((START_RANGE..width).random()),
            )
        }
        return Mines(Cells(minePositions))
    }
}
