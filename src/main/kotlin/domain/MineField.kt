package domain

import domain.Cell.Cell
import domain.Cell.Empty
import domain.Cell.Mine

class MineField {
    data class FieldInfo(val height: Int, val width: Int, val mineCount: Int)

    fun buildField(fieldInfo: FieldInfo): List<List<Cell>> {
        val field: List<MutableList<Cell>> = List(fieldInfo.height) { MutableList(fieldInfo.width) { Empty(0, 0) } }
        val mines: List<Mine> = buildMines(mutableListOf(), fieldInfo)

        return field.onEachIndexed { row, line ->
            line.forEachIndexed { column, _ ->
                if (Mine(row, column) in mines) {
                    field[row][column] = Mine(row, column)
                }
                field[row][column] = Empty(row, column)
            }
            line.toList()
        }
    }

    private fun buildMines(mines: MutableList<Mine>, fieldInfo: FieldInfo): List<Mine> {
        while (mines.size != fieldInfo.mineCount) {
            val mine = mineBuilder(fieldInfo.height, fieldInfo.width)
            if (mine !in mines) {
                mines.add(mine)
            }
        }
        return mines.toList()
    }

    private fun mineBuilder(height: Int, width: Int): Mine {
        val mineRow = (0 until height).random()
        val mineColumn = (0 until width).random()
        return Mine(mineRow, mineColumn)
    }
}
