package model

import dto.BoardDTO

data class Board(
    private val rows: List<Row>
) {
    constructor(vararg rows: Row) : this(rows.asList())

    fun open(position: Position) {
        val cell: Cell = getCell(position)

        if (cell.opened()) {
            return
        }

        cell.open()
        if (aroundSafe(cell)) {
            openAroundCells(cell)
        }
    }

    private fun aroundSafe(cell: Cell): Boolean {
        return cell.aroundPositions()
            .all {
                getCell(it).safe()
            }
    }

    private fun getCell(position: Position): Cell {
        val (width, height) = position.indexPair()

        return rows[height].get(width)
    }

    private fun openAroundCells(cell: Cell) {
        cell.aroundPositions()
            .forEach {
                open(it)
            }
    }

    fun over(): Boolean {
        return winning() || lost()
    }

    fun winning(): Boolean {
        return rows.all {
            it.clean()
        }
    }

    fun lost(): Boolean {
        return rows.any {
            it.containingExploded()
        }
    }

    fun asDTO(): BoardDTO {
        return BoardDTO(
            rows.map { it.asDTO() }
        )
    }
}