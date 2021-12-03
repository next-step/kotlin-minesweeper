package minesweeper.domain.block

import minesweeper.domain.MinesCount
import minesweeper.strategy.BoardGenerateStrategy

@JvmInline
value class Board(val blocks: List<Block>) {

    companion object {
        fun of(area: Area, minesCount: MinesCount, boardGenerateStrategy: BoardGenerateStrategy): Board {
            validateArguments(area, minesCount)
            return Board(boardGenerateStrategy.generate(area.width(), area.height(), minesCount.minesCount).toList())
        }

        private fun validateArguments(area: Area, minesCount: MinesCount) {
            if (area.area() < minesCount.minesCount) {
                throw IllegalArgumentException()
            }
        }
    }
}
// for ((x, mutableList) in board.withIndex()) {
//     for ((y, element) in mutableList.withIndex()) {
//         setNumber(x, y, width.width, height.height, board)
//     }
// }
//
// println(
//     board.joinToString(separator = "\n") {
//         it.fold(StringBuilder()) { acc: StringBuilder, i: Int -> acc.append("$i\t") }
//     }
// )
//
// outputView.startGame()
// println(
//     board.joinToString(separator = "\n") {
//         it.fold(StringBuilder()) { acc: StringBuilder, i: Int ->
//             acc.append(
//                 if (i == -1) "*" else "C"
//             )
//         }
//     }
// )
// 랜덤 값으로 지뢰 주입
//
// fun setNumber(row: Int, column: Int, width: Int, height: Int, board: MutableList<MutableList<Int>>) {
//     if (board[row][column] == 99) {
//         board[row][column] = getMineNumber(row, column, width, height, board)
//     }
// }
//
// private fun getMineNumber(
//     row: Int,
//     column: Int,
//     width: Int,
//     height: Int,
//     board: MutableList<MutableList<Int>>
// ): Int {
//     var mineCnt = 0
//     if (isExistMine(row - 1, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row - 1, column, width, height, board)) mineCnt++
//     if (isExistMine(row - 1, column + 1, width, height, board)) mineCnt++
//     if (isExistMine(row, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row, column + 1, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column + 1, width, height, board)) mineCnt++
//     return mineCnt
// }
//
// private fun isExistMine(
//     row: Int,
//     column: Int,
//     width: Int,
//     height: Int,
//     board: MutableList<MutableList<Int>>
// ): Boolean {
//     if (row < 0 || row >= width || column < 0 || column >= height) {
//         return false
//     }
//
//     return board[row][column] == -1
// }
