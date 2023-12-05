package domain.board

import domain.location.Location
import domain.setting.Height
import domain.setting.MineCount
import domain.setting.Size
import domain.setting.Width

@JvmInline
value class Board private constructor(val value: List<List<Cell>>) {
    companion object {
        fun create(size: Size, mineCount: MineCount): Board {
            val mineLocations = Location.generateMineLocations(size, mineCount)
            return BoardValue.create(size)
                .injectMines(mineLocations)
                .build()
                .let(::Board)
        }

        fun create(height: Height, width: Width, mineCount: MineCount): Board = create(Size(height, width), mineCount)
    }
}

class BoardValue private constructor(private val value: MutableList<MutableList<Cell>>) {
    fun injectMines(mineLocations: Set<Location>): BoardValue {
        mineLocations.forEach {
            require(it.row.value < value.size) { "row must be less than height" }
            require(it.column.value < value[it.row.value].size) { "column must be less than width" }
            value[it.row.value][it.column.value] = Cell.Mine()
        }
        return this
    }

    fun build(): List<List<Cell>> {
        return value.map { it.toList() }.toList()
    }

    companion object {
        fun create(size: Size): BoardValue {
            return MutableList(size.height.value) {
                MutableList<Cell>(size.width.value) {
                    Cell.Tile()
                }
            }.let(::BoardValue)
        }
    }
}
