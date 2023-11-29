package minesweeper.domain

class RandomMineGenerator(
    private val height: Int,
    private val width: Int,
) : MineGenerator {

    override fun generate(count: Int): Mines {
        val list = mutableListOf<Position>()
        for (x in 1..height) {
            (1..width).forEach { y ->
                list.add(Position(x, y))
            }
        }
        return Mines(
            list.shuffled()
                .take(count).associateWith {
                    Mine(it.x, it.y)
                }
        )
    }
}
