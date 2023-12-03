import map.Board
import map.None

object CreateFactory {
    fun createBoard(width: Int, height: Int): Board {
        return Board(MutableList(height) { MutableList(width) { None } })
    }
}
