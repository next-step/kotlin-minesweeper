package minesweeper.domain

class RandomPositionGenerator(
    height: Size,
    width: Size,
) : PositionGenerator(height, width) {

    override fun generate(count: Size): List<Position> {
        checkValidCount(count)
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
