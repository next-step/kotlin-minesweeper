package minesweeper.domain

class RandomPositionGenerator(
    private val height: Size,
    private val width: Size,
) : PositionGenerator {

    fun init(): List<Position> {
        return height.getNumbers()
            .flatMap { rowNum ->
                width.getRows(rowNum)
            }.toList()
    }

    override fun generate(count: Size): List<Position> {
        return (height * width).getNumbers()
            .shuffled()
            .take(count.value)
            .map {
                val x = it / height
                val y = it % width
                Position(x, y)
            }.toList()
    }
}
