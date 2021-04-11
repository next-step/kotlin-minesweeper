import domain.Coordinate
import domain.GameState
import domain.MineBoard
import dto.MineBoardDto

class MineBoardGame(private val mineBoard: MineBoard) {

    private var gameState = GameState.RUNNING

    fun check(x: Int, y: Int) {
        val coordinate = Coordinate(x, y)
        mineBoard.check(coordinate)

        if (mineBoard.notExistsToCheck()) gameState = GameState.WIN
        if (mineBoard.existsCheckedMine()) gameState = GameState.LOSE
    }

    fun result(): MineBoardDto {
        return MineBoardDto(mineBoard)
    }

    fun isRunning(): Boolean {
        return gameState.isRunning()
    }
}
