package minesweeper.domain

class FixedPositionGenerator(
    height: Size,
    width: Size,
) : PositionGenerator(height, width) {

    override fun generate(count: Size): List<Position> {
        return (height * width).getNumbers()
            .take(count.value)
            .map {
                val x = it / height
                val y = it % width
                Position(x, y)
            }.toList()
    }
}
