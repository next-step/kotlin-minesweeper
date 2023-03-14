import domains.GameMap
import domains.GameSize
import domains.MinePositionGenerator
import domains.OpenResult
import views.Input.getHeight
import views.Input.getMine
import views.Input.getWidth
import views.Input.openBlock
import views.Output

fun main() {
    val mapHeight = getHeight()
    val mapWidth = getWidth()
    val mineCount = getMine()

    val gameSize = GameSize(mapWidth, mapHeight)
    val minePositions = MinePositionGenerator(gameSize, mineCount).generateMinePositions()
    val gameMap = GameMap.from(gameSize, minePositions)
    Output.startGame()
    Output.showMap(gameMap)

    val openResult = pickOpen(gameMap)
    Output.printResult(openResult.message)
}

fun pickOpen(gameMap: GameMap): OpenResult {
    var openResult = OpenResult.CONTINUE
    while (openResult == OpenResult.CONTINUE) {
        val openPosition = openBlock()
        val alreadyOpened = gameMap.checkAlreadyOpened(openPosition)
        if (alreadyOpened) {
            Output.alreadyOpenPosition()
            continue
        }
        gameMap.open(openPosition)

        openResult = gameMap.evaluateGameResult()
        if (openResult == OpenResult.LOSE || openResult == OpenResult.WIN) {
            break
        }

        Output.showMap(gameMap)
    }
    return openResult
}
