package minesweeper.io

import minesweeper.domain.Map
import minesweeper.domain.Mine
import minesweeper.domain.Position

class Output {
    fun printMap(map: Map) {
        println(drawHeight(map))
    }

    private fun drawHeight(map: Map): String {
        return (1..map.maxSize.height).joinToString("\n") { height ->
            drawWidth(map.maxSize.width, height, map.mines)
        }
    }

    private fun drawWidth(maxWidth: Int, height: Int, mines: List<Mine>): String {
        return (1..maxWidth).joinToString(" ") { width ->
            if (isMine(mines, Position(width, height))) Map.MINE_STRING else Map.SAFE_STRING
        }
    }

    private fun isMine(mines: List<Mine>, position: Position) =
        mines.any { it.position == position }
}
