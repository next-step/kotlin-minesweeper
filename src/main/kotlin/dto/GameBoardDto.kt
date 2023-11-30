package dto

import domain.GameBoard

data class GameBoardDto(val board: List<List<String>>) {
    constructor(board: GameBoard) : this(board.board.map { row ->
        row.cells.map { cell ->
            cell.cellInfo.cellType.symbol
        }
    })
}
