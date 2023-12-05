package dto

import domain.GameBoard

data class GameResultDto(val status: String) {
    constructor(gameBoard: GameBoard) : this(gameBoard.gameResult.gameStatus.name)
}
