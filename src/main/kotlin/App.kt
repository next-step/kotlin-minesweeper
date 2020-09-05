import model.Game
import view.Input
import view.Output

fun main() {
    val width = Input.inputWidth()
    val height = Input.inputHeight()
    val map = Game(width, height).apply {
        createDefaultMap(width, height)
        createCountMap()
        createRandomMines(Input.inputMine(width * height))
        calculateCount()
    }
    while (true) {
        map.openMap(Input.inputPosition(width, height))
        Output.drawMap(map)
        if (map.winCheck()) {
            Output.win()
            break
        }
    }
}
