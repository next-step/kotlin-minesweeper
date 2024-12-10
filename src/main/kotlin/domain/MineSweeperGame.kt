package domain

class MineSweeperGame(
    private val mineShape: MineShape,
    val mines: Set<Mine> = setOf(),
) {
    fun getWidth(): Int {
        return mineShape.width
    }

    fun getHeight(): Int {
        return mineShape.height
    }

    companion object {
        fun makeGame(
            width: Int,
            height: Int,
            numberOfMine: Int,
            mineGenerator: MineGenerator = RandomMineGenerator(),
        ): MineSweeperGame {
            val mines = mineGenerator.getMine(width, height, numberOfMine)
            return MineSweeperGame(MineShape(width, height), mines)
        }
    }
}
