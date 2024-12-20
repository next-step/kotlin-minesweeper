package minesweeper

import kotlin.random.Random

class Board2(
    private val dimensions: Dimensions,
    minePositions: Set<Position>
) {
    init {
        require(minePositions.isNotEmpty()) { "마인은 최소 ${MIN_MINE_COUNT}개 이상 이어야 합니다." }
        require(minePositions.size < dimensions.totalCells) { "마인의 수는 전체 셀 수보다 작아야 합니다." }
    }

    private val cells: Cells = initializeCells(minePositions)

    private fun initializeCells(minePositions: Set<Position>): Cells {
        val cellList = dimensions.allPositions().map { position ->
            if (minePositions.contains(position)) {
                Cell.createMine(position)
            } else {
                Cell.createDefault(position)
            }
        }
        return Cells.detectCreateOf(cellList)
    }

    fun checkMine(position: Position): Boolean {
        return cells.checkMine(position)
    }

    fun draw(): BoardDrawing {
        return BoardDrawing.create(cells)
    }

    companion object {
        const val MIN_MINE_COUNT = 1
    }
}

class MinePlacer(
    private val dimensions: Dimensions,
    private val mineCount: Int
) {
    init {
        require(mineCount in 1 until dimensions.totalCells) {
            "Mine count must be less than total cells and greater than 0."
        }
    }

    fun placeMines(): Set<Position> {
        val positions = mutableSetOf<Position>()
        while (positions.size < mineCount) {
            val x = Random.nextInt(dimensions.width)
            val y = Random.nextInt(dimensions.height)
            positions.add(Position(x, y))
        }
        return positions
    }
}