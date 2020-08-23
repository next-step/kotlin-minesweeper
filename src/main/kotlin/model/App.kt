package model

import view.Input
import view.Output

fun main() {
    val width = Input.inputWidth()
    val height = Input.inputHeight()
    val mineCount = Input.inputMine(width * height)
    val map = Map(width, height, mineCount)
    Output.drawMap(map)
}
