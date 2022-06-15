package domain

class RandomMineGenerator(private val height: Int, private val width: Int) : MineGenerator {
    private val allMines = List(height * width) {
        MinePosition(it / width, it % width)
    }.shuffled().toMutableList()

    override fun generateMine(): MinePosition {
        return allMines.removeLast()
    }
}
