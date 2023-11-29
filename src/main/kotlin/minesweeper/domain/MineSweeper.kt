package minesweeper.domain

class MineSweeper(
    val mineSweeperSize: MineSweeperSize,
    minePosition: List<Int>
) {
    val mineMap: List<List<Cell>> = List(mineSweeperSize.height) { y ->
        List(mineSweeperSize.width) { x ->
            Cell(isMine = minePosition.contains(y * mineSweeperSize.height + x))
        }
    }
}
