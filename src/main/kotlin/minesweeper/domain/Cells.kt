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

    fun cellAllOpen(): Cells = from(_cells.map { it.open() })

    fun isSafetyCellAllOpen(): Boolean = _cells.filter { !it.isMine() }.all { it.openState == OpenState.OPENED }

    private fun openEmptyCells(position: Position): Cells {
        val queue: Queue<Position> = LinkedList(listOf(position))
        val cellsMap = cells.associateBy { it.position }.toMutableMap()
        while (queue.isNotEmpty()) {
            var nowCell = cellsMap[queue.poll()]
            if (nowCell!!.isOpen()) {
                continue
            }
            nowCell = nowCell.open()
            cellsMap[nowCell.position] = nowCell
            if (nowCell.aroundSafetyCells(safetyCells()).isEmpty()) {
                continue
            }
            val aroundSafetyCellPositions = nowCell.filterAroundCellPositions(safetyCellPositions())
            val aroundMineCells = nowCell.aroundMineCells(mineCells())
            if (aroundMineCells.isEmpty()) {
                for (i in aroundSafetyCellPositions.indices) {
                    if (cellsMap[aroundSafetyCellPositions[i]]!!.openState == OpenState.CLOSED && (cellsMap[aroundSafetyCellPositions[i]] as SafetyCell).isNotContainAroundMine()) {
                        queue.offer(aroundSafetyCellPositions[i])
                    }
                    if (cellsMap[aroundSafetyCellPositions[i]]!!.aroundMineCells(mineCells()).isNotEmpty()) {
                        cellsMap[aroundSafetyCellPositions[i]] = cellsMap[aroundSafetyCellPositions[i]]!!.open()
                    }
                }
            }
        }
        return from(cellsMap.values.toList())
    }

    private fun mineCells(): List<MineCell> {
        return _cells.filterIsInstance<MineCell>()
    }

    private fun safetyCellPositions(): List<Position> {
        return safetyCells()
            .map { it.position }
    }

    private fun safetyCells(): List<SafetyCell> {
        return _cells.filterIsInstance<SafetyCell>()
    }

    private fun getCell(position: Position): Cell {
        return _cells.firstOrNull { it.position == position } ?: throw IllegalArgumentException(CELL_POSITION_NULL_POINTER_EXCEPTION_MESSAGE)
    }

    companion object {

        private const val CELL_POSITION_NULL_POINTER_EXCEPTION_MESSAGE = "해당 위치에 CELL이 존재하지 않습니다."

        fun of(positions: List<Position>, minePositions: List<Position>): Cells {
            return from(
                positions.map {
                    if (minePositions.contains(it)) {
                        MineCell(it)
                    } else {
                        SafetyCell(it, it.countAroundPositionsContainOthers(minePositions))
                    }
                }
            )
        }

        fun from(cellsList: List<Cell>): Cells {
            return Cells(cellsList.sortedBy { it.position })
        }
    }
}
