package domain

class MineSweeperGame(
    private val mineShape: MineShape,
    val mines: MineSet = MineSet(setOf()),
) {
    fun getWidth(): Int {
        return mineShape.width
    }

    fun getHeight(): Int {
        return mineShape.height
    }

    fun getMineMap(): MineMap {
        return mines.getMineMap()
    }

    companion object {
        fun makeGame(
            width: Int,
            height: Int,
            numberOfMine: Int,
            minesGenerator: MinesGenerator = RandomMinesGenerator(),
        ): MineSweeperGame {
            val mines = minesGenerator.getMines(width, height, numberOfMine)
            return MineSweeperGame(MineShape(width, height), mines)
        }
    }
}
