package domain.field

import domain.cell.Cell
import domain.cell.Empty
import domain.cell.Mine
import domain.Mines

class Field {

    fun buildField(fieldInfo: FieldInfo): List<List<Cell>> {
        val field: List<MutableList<Cell>> = List(fieldInfo.height) { MutableList(fieldInfo.width) { Empty(0, 0) } }
        val mines: Mines = Mines().buildMines(fieldInfo)

        return field.onEachIndexed { row, line ->
            line.forEachIndexed { column, _ ->
                field[row][column] = Empty(row, column)
                if (Mine(row,column) in mines) {
                    field[row][column] = Mine(row, column)
                    println("yes")
                }
            }
            line.toList()
        }
    }
}
