package domain

class MineSweeperGame(
    private val mineSweeperMapShape: MineSweeperMapShape,
    val mines: MineSet = MineSet(setOf()),
) {
    fun getWidth(): Int {
        return mineSweeperMapShape.width
    }

    fun getHeight(): Int {
        return mineSweeperMapShape.height
    }

    companion object {
        fun makeGame(
            width: Int,
            height: Int,
            numberOfMine: Int,
            minesGenerator: MinesGenerator = RandomMinesGenerator(),
        ): MineSweeperGame {
            val mines = minesGenerator.getMines(width, height, numberOfMine)
            return MineSweeperGame(MineSweeperMapShape(width, height), mines)
        }
    }
}
