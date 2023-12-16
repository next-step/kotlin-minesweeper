package minesweeper.board

fun interface GameBoardRenderStrategy {

    operator fun invoke(width: Element, height: Element, init: Char): Array<Array<Char>>
}

val defaultGameBoardRenderStrategy = GameBoardRenderStrategy { width, height, cell ->
    Array(height.value) { Array(width.value) { cell } }
}
