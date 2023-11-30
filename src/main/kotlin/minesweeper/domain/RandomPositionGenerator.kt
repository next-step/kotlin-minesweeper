package minesweeper.domain

class RandomPositionGenerator(
    private val height: Size,
    private val width: Size,
) : PositionGenerator {

    override fun generate(count: Size): List<Position> {
        val multiple = height * width
        return multiple.getRandomNumbers(count)
            .map {
                val x = it / height
                val y = it % width
                Position(x, y)
            }
            .toList()
    }
}
