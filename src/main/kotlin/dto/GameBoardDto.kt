package dto

import domain.GameBoard

data class GameBoardDto(val board: List<List<CellTypeDto>>) {
    constructor(board: GameBoard) : this(board.board.map { row ->
        row.cells.map { cell ->
            CellTypeDto(cell.cellInfo.cellType.symbol, cell.cellInfo.neighborMineCount.count, cell.cellInfo.isOpened)
        }
    })
}
