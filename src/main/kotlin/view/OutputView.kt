package view

import dto.BoardDTO
import dto.CellDTO
import model.CellType
import model.State

object OutputView {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(boardDTO: BoardDTO) {
        boardDTO.rowDTOs.forEach { rowDTO ->
            rowDTO.cellDTOs.forEach { cellDTO ->
                printCell(cellDTO, boardDTO)
            }
            println()
        }
    }

    private fun printCell(cellDTO: CellDTO, boardDTO: BoardDTO) {
        if (cellDTO.state == State.CLOSE) {
            print("C ")
            return
        }

        if (cellDTO.cellType == CellType.Mine) {
            print("* ")
            return
        }

        print("${calculateMineCount(cellDTO, boardDTO)} ")
    }

    private fun calculateMineCount(cellDTO: CellDTO, boardDTO: BoardDTO) = cellDTO.position.aroundPositions().map {
        if (boardDTO.rowDTOs[it.indexPair().second].cellDTOs[it.indexPair().first].cellType == CellType.Mine) 1 else 0
    }.sum()

    fun printWin() {
        println("Win Game.")
    }

    fun printLose() {
        println("Lose Game.")
    }
}