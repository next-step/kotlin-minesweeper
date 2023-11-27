package minesweeper

class RandomMineGenerator(
    private val height: Int,
    private val width: Int,
) : MineGenerator {

    override fun generate(count: Int): Mines {
        val list = mutableListOf<Pair<Int, Int>>()
        for (x in 1..height) {
            (1..width).forEach { y ->
                list.add(Pair(x, y))
            }
        }
        return Mines(
            list.shuffled()
                .take(count).associateWith {
                    Mine(it.first, it.second)
                }
        )
    }
}
