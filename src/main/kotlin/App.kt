import model.Game
import model.MapSize
import model.MineCount
import model.Size
import view.Input
import view.Output

fun main() {
    val width = Size(Input.width())
    val height = Size(Input.height())
    val mapSize = MapSize(width, height)
    val mineCount = MineCount(Input.mineCount(mapSize.size), mapSize.size)
    val game = Game(mapSize, mineCount)

    while (true) {
        game.openMap(Input.position(width.value, height.value))
        Output.drawMap(game.viewMap, width)
        if (game.win()) Output.win()
    }
}
