import model.Length
import model.MapSize
import model.MineCount
import model.MineGame
import view.Input
import view.Output

fun main() {
    val width = Length(Input.width())
    val height = Length(Input.height())
    val mapSize = MapSize(width, height)
    val mapTotalCellCount = mapSize.lengthX.value * mapSize.lengthY.value
    val mineCount = MineCount(Input.mineCount(mapTotalCellCount), mapTotalCellCount)
    val game = MineGame(mapSize, mineCount)

    while (true) {
        game.openMap(Input.position(width.value, height.value))
        Output.drawMap(game.map)
        if (game.win()) Output.win()
    }
}
