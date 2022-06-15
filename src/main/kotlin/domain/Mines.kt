package domain

import domain.cell.Mine
import domain.field.FieldInfo

class Mines {
    private val minePositions = mutableListOf<Mine>()

    fun buildMines(fieldInfo: FieldInfo): Mines {
        while (minePositions.size != fieldInfo.mineCount) {
            val mine = mineBuilder(fieldInfo.height, fieldInfo.width)
            if (mine !in minePositions) {
                minePositions.add(mine)
            }
        }

        minePositions.forEach { println(it) }
        return this
    }

    private fun mineBuilder(lastRow: Int, lastColumn: Int): Mine {
        val mineRow = (0 until lastRow).random()
        val mineColumn = (0 until lastColumn).random()
        return Mine(mineRow, mineColumn)
    }

    operator fun contains(mine: Mine) : Boolean{
        return minePositions.contains(mine)
    }
}