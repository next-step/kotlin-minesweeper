package minesweeper.board

fun interface GameBoardRenderStrategy {

    operator fun invoke(boardDimensions: BoardDimensions, default: Char): RenderedGameBoard

    companion object {
        const val MINE = '*'
    }
}
