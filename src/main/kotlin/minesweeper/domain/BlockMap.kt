package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width

class BlockMap(
    height: Height,
    width: Width,
    mineCount: MineCount,
    mineGenerator: MineGenerator = RandomMineGenerator(),
    private val _blockRows: MutableList<BlockRow> = mutableListOf(),
) {

    constructor(width: Int, height: Int, mineCount: Int) : this(
        Height(height),
        Width(width),
        MineCount(mineCount),
    )

    val blocks: List<BlockRow>
        get() = _blockRows.toList()

    val width: Int
        get() = blocks.first().length

    val height: Int
        get() = blocks.size

    init {
        val mines = mineGenerator.generate(mineCount, height, width)

        MutableList(height.value) {
            val row = BlockRow(it, width.value)
            mines.forEach { mine ->
                if (row.contains(mine)) row.find(mine)?.mine()
            }
            row
        }
            .forEach(_blockRows::add)
    }

    fun find(point: Point): Block? = blocks.find { it.contains(point) }?.find(point)

    companion object {
        const val MIN_MINE_COUNT = 1
        const val MIN_WIDTH = 1
        const val MIN_HEIGHT = 1
    }
}

fun maxMineCount(width: Int, height: Int): Int = width * height
