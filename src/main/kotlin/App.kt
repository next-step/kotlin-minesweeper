import model.Map
import view.Input
import view.Output

fun main() {
    val width = Input.inputWidth()
    val height = Input.inputHeight()
    val map = Map(width, height).apply {
        createDefaultMap(width, height)
        createRandomMines(Input.inputMine(width * height))
    }
    Output.drawMap(map)
}
