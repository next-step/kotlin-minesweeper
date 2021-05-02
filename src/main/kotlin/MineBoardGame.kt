import domain.Coordinate
import domain.GameState
import domain.MineBoard
import dto.MineGameDto

class MineBoardGame(private val mineBoard: MineBoard) {

    private var gameState = GameState.RUNNING

    fun open(x: Int, y: Int) {
        val coordinate = Coordinate(x, y)
        mineBoard.open(coordinate)

        if (mineBoard.notExistsToOpen()) gameState = GameState.WIN
        if (mineBoard.existsOpenedMine()) gameState = GameState.LOSE
    }

    fun result(): MineGameDto {
        return MineGameDto(gameState, mineBoard)
    }

    fun isRunning(): Boolean {
        return gameState.isRunning()
    }
}
