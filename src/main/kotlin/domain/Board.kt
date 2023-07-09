package domain

data class Board(
    val rows: List<Spaces>,
) {

    companion object {
        fun create(boardSize: BoardSize, minePositionGenerator: NumbersGenerator): Board {
            val spaces = emptySpaces(boardSize)
            spaces.plantMinesAt(minePositionGenerator.generate())
            return Board(spaces.chunked(boardSize.width.value))
        }

        private fun emptySpaces(boardSize: BoardSize): Spaces {
            return Spaces.emptySpaces(boardSize.numberOfSpaces)
        }
    }
}
