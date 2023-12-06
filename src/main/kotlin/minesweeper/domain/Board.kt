package minesweeper.domain

import minesweeper.domain.rule.MineGenerationRule
import java.util.*

class Board(val metadata: BoardMetadata, rule: MineGenerationRule) {
    private val rows: Map<Coordinate, Cell>
    private val openedCoordinates = mutableSetOf<Coordinate>()

    init {
        rows = rule.generate(metadata)
    }

    fun at(row: Int, col: Int): Cell {
        return rows[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    fun openedCoordinates(): Set<Coordinate> {
        return openedCoordinates.toSet()
    }

    fun canOpen(coordinate: Coordinate): Boolean {
        val currentCell = at(coordinate.row, coordinate.col)
        return currentCell is EmptyCell
    }

    fun open(coordinate: Coordinate, countingBoard: CountingBoard) {
        val currentCell = at(coordinate.row, coordinate.col)
        if (currentCell is MineCell) {
            return
        }

        if (countingBoard.countAroundMine(coordinate.row, coordinate.col) > 0) {
            currentCell.open()
            openedCoordinates.add(coordinate)
        }

        return openAllAround(coordinate, countingBoard)
    }

    fun isAllOpened(): Boolean {
        return rows.filter { it.value is EmptyCell }.all { it.value.isOpened }
    }

    private fun openAllAround(coordinate: Coordinate, countingBoard: CountingBoard) {
        val queue: Queue<Coordinate> = LinkedList()
        queue.offer(coordinate)

        while (queue.isNotEmpty()) {
            val currentCoordinate = queue.poll()
            at(currentCoordinate.row, currentCoordinate.col).open()
            openedCoordinates.add(currentCoordinate)
            if (countingBoard.countAroundMine(currentCoordinate.row, currentCoordinate.col) > 0) continue

            visitAround(currentCoordinate, queue)
        }
    }

    private fun visitAround(
        currentCoordinate: Coordinate,
        queue: Queue<Coordinate>
    ) {
        for (aroundCoordinate in AROUND_COORDINATES) {
            val nextCoordinate = currentCoordinate + aroundCoordinate
            if (nextCoordinate.isOutOfBound(MIN_HEIGHT, metadata.height, MIN_WIDTH, metadata.width)) continue

            when (val nextCell = at(nextCoordinate.row, nextCoordinate.col)) {
                is MineCell -> continue
                is EmptyCell -> {
                    if (nextCell.isOpened) continue
                }
            }

            queue.offer(nextCoordinate)
        }
    }

    companion object {
        const val MIN_HEIGHT = 0
        const val MIN_WIDTH = 0
        val AROUND_COORDINATES = listOf(
            Coordinate(-1, -1),
            Coordinate(-1, 0),
            Coordinate(-1, 1),
            Coordinate(0, -1),
            Coordinate(0, 1),
            Coordinate(1, -1),
            Coordinate(1, 0),
            Coordinate(1, 1)
        )
    }
}
