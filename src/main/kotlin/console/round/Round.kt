package console.round

import map.Height
import map.Map
import map.Width
import map.move.Position
import open.result.OpenResult

class Round(
    val map: Map,
) {
    fun play(choosePosition: (Height, Width) -> Position?): Map? {
        val height = map.height
        val width = map.width
        val position = choosePosition(height, width) ?: Position.default(height = height, width = width)

        return when (val result = map.open(position = position)) {
            is OpenResult.Success -> result.map
            is OpenResult.InvalidPosition -> map
            is OpenResult.MineExploded -> null
        }
    }
}
