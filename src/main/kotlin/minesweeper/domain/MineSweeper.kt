package minesweeper.domain

class MineSweeper(
    val mineSweeperSize: MineSweeperSize,
    minePosition: List<Int>
) {
    val mineMap: Map<Int, List<Cell>> = (0 until mineSweeperSize.height).associateWith { y ->
        (0 until mineSweeperSize.width).map { x ->
            Cell(isMine = minePosition.contains(y * mineSweeperSize.height + x))
        }
    }
}
