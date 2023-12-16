package minesweeper.board

fun interface GameBoardRenderStrategy {

    operator fun invoke(boardElement: BoardElement, init: Char): Array<Array<Char>>
}

val defaultGameBoardRenderStrategy = GameBoardRenderStrategy { boardElement, cell ->
    Array(boardElement.height) { Array(boardElement.width) { cell } }
}
