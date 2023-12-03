package minesweeper.domain

import minesweeper.domain.rule.MineGenerationRule
import java.util.*

class Board(private val metadata: BoardMetadata, rule: MineGenerationRule) {
    private val rows: Map<Coordinate, Cell>
    private val countsOfAroundMines: MutableMap<Coordinate, Int> = mutableMapOf()
    private val openedCoordinates = mutableSetOf<Coordinate>()

    init {
        rows = rule.generate(metadata)
        countAllAroundMine()
    }

    fun at(row: Int, col: Int): Cell {
        return rows[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    fun countOf(row: Int, col: Int): Int {
        return countsOfAroundMines[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    fun countAllAroundMine() {
        rows.filter { it.value is EmptyCell }.forEach {
            countsOfAroundMines[it.key] = countAroundMine(it.key)
        }
    }

    fun canOpen(coordinate: Coordinate): Boolean {
        val currentCell = at(coordinate.row, coordinate.col)
        return currentCell is EmptyCell
    }

    fun open(coordinate: Coordinate): List<Coordinate> {
        val currentCell = at(coordinate.row, coordinate.col)
        if (currentCell is MineCell) {
            return emptyList()
        }

        if (countOf(coordinate.row, coordinate.col) > 0) {
            openedCoordinates.add(coordinate)
            return listOf(coordinate)
        }

        return openAllAround(coordinate)
    }

    fun isAllOpened(): Boolean {
        return rows.filter { it.value is EmptyCell }.all { openedCoordinates.contains(it.key) }
    }

    private fun countAroundMine(coordinate: Coordinate): Int {
        var mineCount = 0
        for (aroundCoordinate in AROUND_COORDINATES) {
            val nextCoordinate = coordinate + aroundCoordinate
            if (nextCoordinate.isOutOfBound(MIN_HEIGHT, metadata.height, MIN_WIDTH, metadata.width)) continue
            if (at(nextCoordinate.row, nextCoordinate.col) is MineCell) {
                mineCount++
            }
        }
        return mineCount
    }

    private fun openAllAround(coordinate: Coordinate): List<Coordinate> {
        val results = mutableListOf<Coordinate>()
        val queue: Queue<Coordinate> = LinkedList()
        queue.offer(coordinate)

        while (queue.isNotEmpty()) {
            val currentCoordinate = queue.poll()
            openedCoordinates.add(currentCoordinate)
            results.add(currentCoordinate)
            if (countOf(currentCoordinate.row, currentCoordinate.col) > 0) continue

            visitAround(currentCoordinate, queue)
        }

        return results
    }

    private fun visitAround(
        currentCoordinate: Coordinate,
        queue: Queue<Coordinate>
    ) {
        for (aroundCoordinate in AROUND_COORDINATES) {
            val nextCoordinate = currentCoordinate + aroundCoordinate
            if (nextCoordinate.isOutOfBound(MIN_HEIGHT, metadata.height, MIN_WIDTH, metadata.width)) continue
            if (openedCoordinates.contains(nextCoordinate)) continue
            if (at(nextCoordinate.row, nextCoordinate.col) is MineCell) continue
            queue.offer(nextCoordinate)
        }
    }

    companion object {
        const val MIN_HEIGHT = 0
        const val MIN_WIDTH = 0
        private val AROUND_COORDINATES = listOf(
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
