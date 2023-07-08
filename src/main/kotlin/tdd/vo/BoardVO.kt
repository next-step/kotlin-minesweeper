package tdd.vo

import tdd.domain.Board

class BoardVO(
    val rows: List<List<CellVO>>
) {
    companion object {
        operator fun invoke(board: Board): BoardVO {
            val boardRows = board.cells.entries
                .groupBy({ it.key.row }, { it.key.col to it.value })
                .map { (_, values) ->
                    values.sortedBy { it.first }
                        .map { CellVO(it.second) }
                }
            return BoardVO(boardRows)
        }
    }
}
