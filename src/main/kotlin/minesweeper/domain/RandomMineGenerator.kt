package minesweeper.domain

class RandomMineGenerator(
    private val height: Size,
    private val width: Size,
) : MineGenerator {

    override fun generate(count: Size): Mines {
        val list = mutableListOf<Position>()
        for (x in 1..height.value) {
            (1..width.value).forEach { y ->
                list.add(Position(x, y))
            }
        }
        return Mines(
            list.shuffled()
                .take(count.value).associateWith {
                    Mine(it.x, it.y)
                }
        )
    }
}
