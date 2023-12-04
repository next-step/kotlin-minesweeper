package minesweeper.domain

object MineCreator {
    fun create(
        mineSweeperSize: MineSweeperSize,
        countOfMine: Int
    ): List<Int> {
        require(mineSweeperSize.getArea() >= countOfMine) {
            "Mines are too many!"
        }

        return (0 until mineSweeperSize.getArea()).shuffled()
            .slice(0 until countOfMine)
            .toList()
    }
}
