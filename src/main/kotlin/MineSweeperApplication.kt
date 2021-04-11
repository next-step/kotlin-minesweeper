import domain.MineBoardFactory
import domain.coordinategenerator.BoardCoordinatesGenerator
import domain.coordinategenerator.RandomCoordinatesGenerator
import userinterface.Console
import userinterface.UserInterface

fun main() {
    val console = Console
    val app = MineSweeperApplication(console)
    app.run()
}

class MineSweeperApplication(private val userInterface: UserInterface) {

    fun run() {
        val (height, width, mineCount) = userInterface.inputMineSweeperWidthHeightCount()
        val mineBoard = MineBoardFactory.create(
            boardCoordinatesGenerator = BoardCoordinatesGenerator(
                width = width,
                height = height
            ),
            mineCoordinatesGenerator = RandomCoordinatesGenerator(
                maxX = width,
                maxY = height,
                coordinateCount = mineCount
            )
        )

        val game = MineBoardGame(mineBoard.getSurroundingMineCountedBoard())

        while (true) {
            val (x, y) = userInterface.inputCheckCoordinate()
            game.check(x, y)
            userInterface.outputMineSweeper(game.result())
        }
    }
}
