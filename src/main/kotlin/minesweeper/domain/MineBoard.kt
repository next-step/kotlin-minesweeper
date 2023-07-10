package minesweeper.domain

class MineBoard(val board: List<List<Cell>>) {
    companion object {
        fun generate(width: PositiveNumber, cells: List<Cell>): MineBoard {
            require(cells.size % width.value == 0) { "셀의 개수는 너비의 배수여야 합니다." }

            val board = cells.chunked(width.value)
            return MineBoard(board)
        }
    }
}
