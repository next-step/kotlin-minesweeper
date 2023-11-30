package minesweeper.domain

abstract class PositionGenerator(
    protected val height: Size,
    protected val width: Size,
) {

    fun init(): List<Position> {
        return height.getNumbers()
            .flatMap { rowNum ->
                width.getRows(rowNum)
            }.toList()
    }

    abstract fun generate(count: Size): List<Position>
}
