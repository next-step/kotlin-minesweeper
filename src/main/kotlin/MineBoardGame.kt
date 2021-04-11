import domain.Coordinate
import domain.MineBoard

class MineBoardGame(private val mineBoard: MineBoard) {

    fun check(x: Int, y: Int) {
        val coordinate = Coordinate(x, y)
        mineBoard.check(coordinate)
    }
}
