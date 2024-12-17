package domain

class Game(private val height: Int, private val width: Int, private val mineCount: Int) {
    private val mineField = MineField(height, width, mineCount)

    fun start() {
        mineField.display().forEach { println(it) }
    }
}
