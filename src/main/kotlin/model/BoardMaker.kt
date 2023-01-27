package model

class BoardMaker {
    fun run(boardHeight: BoardHeight, boardWidth: BoardWidth, mine: Mine): List<String> {
        val board = MutableList(boardHeight.height * boardWidth.width - mine.size) { TILE }
        val mines = MutableList(mine.size) { MINE }
        board.addAll(mines)
        return board.shuffled()
    }

    companion object {
        private const val TILE = "C"
        private const val MINE = "*"
    }
}
