import domains.GameMap
import domains.GameSize
import domains.MinePositionGenerator
import views.Input.getHeight
import views.Input.getMine
import views.Input.getWidth
import views.Output.showMap

fun main() {
    val mapHeight = getHeight()
    val mapWidth = getWidth()
    val mineCount = getMine()

    val gameSize = GameSize(mapWidth, mapHeight)
    val minePositions = MinePositionGenerator(gameSize, mineCount).generateMinePositions()
    val gameMap = GameMap.from(gameSize, minePositions)
    gameMap.setNormalBlocks()
    showMap(gameSize, gameMap)
}
