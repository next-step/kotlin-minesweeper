package minesweeper.domain

class MineSweeper(val mineSweeperMap: MineSweeperMap, val mines: Mines) {
    init {
        validateMines()
    }

    private fun validateMines() {
        mines.mines.forEach {
            throwExceptionIfMineInMap(it.position)
        }
    }

    private fun throwExceptionIfMineInMap(position: Position) {
        if (!mineSweeperMap.isInMap(position)) {
            throw IllegalArgumentException(ERROR_MESSAGE)
        }
    }
    companion object {
        private const val ERROR_MESSAGE = "지뢰의 위치가 지뢰지도의 범위를 벗어났습니다."
    }
}
