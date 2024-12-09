package domain

class MineSweeperGame(
    val width: Int,
    val height: Int,
    val mineList: Set<Mine> = setOf(),
) {
    companion object {
        fun makeMap(
            width: Int,
            height: Int,
            numberOfMine: Int,
            mineGenerator: MineGenerator = RandomMineGenerator(),
        ): MineSweeperGame {
            val mines = mineGenerator.getMine(width, height, numberOfMine)
            return MineSweeperGame(width, height, mines)
        }
    }
}
