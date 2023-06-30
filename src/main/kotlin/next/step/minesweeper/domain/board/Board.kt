package next.step.minesweeper.domain.board

data class Board(private val height: BoardHeight, private val width: BoardWidth) {

    fun width(): Int = width.width

    fun height(): Int = height.height
    
    fun area(): Int = width() * height()

    companion object {
        fun of(height: BoardHeight, width: BoardWidth): Board = Board(height, width)
    }
}