package minesweeper.dto

import minesweeper.domain.BoardField
import minesweeper.domain.BoardFields
import minesweeper.domain.MineField
import minesweeper.domain.NumberField

class MineSweeperResponse(
    val boardRowResponses: List<BoardRowResponse>,
) {
    companion object {
        fun of(boardFields: BoardFields, width: Int): MineSweeperResponse {
            val boardRowResponses = boardFields.boardFields.chunked(width)
                .map { BoardRowResponse.of(it, boardFields) }
            return MineSweeperResponse(boardRowResponses)
        }
    }
}

class BoardRowResponse(
    val boardFieldResponses: List<BoardFieldResponse>
) {
    companion object {
        fun of(rowFields: List<BoardField>, boardFields: BoardFields): BoardRowResponse {
            val boardFieldResponses = rowFields.map { BoardFieldResponse.of(it, boardFields) }
            return BoardRowResponse(boardFieldResponses)
        }
    }
}

sealed interface BoardFieldResponse {

    companion object {
        fun of(boardField: BoardField, boardFields: BoardFields): BoardFieldResponse {
            return if (!boardField.isOpen) {
                NotOpenedResponse
            } else {
                openedBoardField(boardField, boardFields)
            }
        }

        private fun openedBoardField(boardField: BoardField, boardFields: BoardFields): BoardFieldResponse {
            return when (boardField) {
                is MineField -> MineFieldResponse
                is NumberField -> NumberFieldResponse(boardField.number(boardFields))
            }
        }
    }
}

data class NumberFieldResponse(val number: Int) : BoardFieldResponse
object MineFieldResponse : BoardFieldResponse
object NotOpenedResponse : BoardFieldResponse
