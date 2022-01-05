package minesweeper.domain

import java.util.LinkedList
import java.util.Queue

@JvmInline
value class Cells private constructor(private val _cells: List<Cell>) {

    val cells: List<Cell>
        get() = _cells.toList()

    fun isMine(position: Position): Boolean {
        return getCell(position).isMine()
    }

    fun open(position: Position): Cells {
        if (isMine(position)) {
            return this
        }
        return openEmptyCells(position)
    }

    fun allOpen(): Cells = from(_cells.map { it.open() })

    fun isAllOpen(): Boolean = _cells.filter { !it.isMine() }.all { it.isOpen }

    private fun openEmptyCells(position: Position): Cells {
        val queue: Queue<Position> = LinkedList(listOf(position))
        val cellsMap = cells.associateBy { it.position }.toMutableMap()
        while (queue.isNotEmpty()) {
            var nowCell = cellsMap[queue.poll()]
            if (nowCell!!.isOpen) {
                continue
            }
            nowCell = (nowCell as Cell.SafetyCell).open()
            cellsMap[nowCell.position] = nowCell
            val aroundSafetyCells = nowCell.aroundSafetyCells()
            if (aroundSafetyCells.isEmpty()) {
                continue
            }
            val aroundSafetyCellPositions = nowCell.aroundSafetyCellPositions()
            val aroundMineCells = nowCell.aroundMineCells()
            if (aroundMineCells.isEmpty()) {
                for (i in aroundSafetyCellPositions.indices) {
                    if (!cellsMap[aroundSafetyCellPositions[i]]!!.isOpen && (cellsMap[aroundSafetyCellPositions[i]] as Cell.SafetyCell).isNotContainAroundMine()) {
                        queue.offer(aroundSafetyCellPositions[i])
                    }
                    if (cellsMap[aroundSafetyCellPositions[i]]!!.aroundMineCells().isNotEmpty()) {
                        cellsMap[aroundSafetyCellPositions[i]] = cellsMap[aroundSafetyCellPositions[i]]!!.open()
                    }
                }
            }
        }
        return from(cellsMap.values.toList())
    }

    private fun Cell.aroundMineCells(): List<Cell.MineCell> {
        return mineCells().filter { aroundMineCellPositions().contains(it.position) }
    }

    private fun Cell.aroundMineCellPositions(): List<Position> {
        return aroundPositions().filter { mineCellPositions().contains(it) }
    }

    private fun mineCellPositions(): List<Position> {
        return mineCells().map { it.position }
    }

    private fun mineCells(): List<Cell.MineCell> {
        return _cells.filterIsInstance<Cell.MineCell>()
    }

    private fun Cell.aroundSafetyCells(): List<Cell> {
        return _cells.filter { aroundSafetyCellPositions().contains(it.position) }
    }

    private fun Cell.aroundSafetyCellPositions(): List<Position> {
        return aroundPositions().filter { safetyCellPositions().contains(it) }
    }

    private fun safetyCellPositions(): List<Position> {
        return safetyCells()
            .map { it.position }
    }

    private fun safetyCells(): List<Cell.SafetyCell> {
        return _cells.filterIsInstance<Cell.SafetyCell>()
    }

    private fun getCell(position: Position): Cell {
        return _cells.firstOrNull { it.position == position } ?: throw IllegalArgumentException(CELL_POSITION_NULL_POINTER_EXCEPTION_MESSAGE)
    }

    companion object {

        const val CELL_POSITION_NULL_POINTER_EXCEPTION_MESSAGE = "해당 위치에 CELL이 존재하지 않습니다."

        fun of(positions: List<Position>, minePositions: List<Position>): Cells {
            return from(
                positions.map {
                    if (minePositions.contains(it)) {
                        Cell.MineCell(it)
                    } else {
                        Cell.SafetyCell(it, it.countAroundPositionsContainOthers(minePositions), false)
                    }
                }
            )
        }

        fun from(cellsList: List<Cell>): Cells {
            return Cells(cellsList.sortedBy { it.position })
        }
    }
}
