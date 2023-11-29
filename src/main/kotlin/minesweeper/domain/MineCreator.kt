package minesweeper.domain

object MineCreator {
    fun create(
        mineSweeperSize: MineSweeperSize,
        countOfMine: Int
    ): List<Int> {

        require(mineSweeperSize.getArea() >= countOfMine) {
            "Mines are too many!"
        }

        return MutableList(mineSweeperSize.getArea()) { it }
            .also {
                it.shuffle()
            }.slice(0 until countOfMine)
            .toList()
    }
}
