package minesweeper.domain

import minesweeper.model.Point

class BlockMap(
    width: Int,
    height: Int,
    mineCount: Int,
    mineGenerator: MineGenerator = RandomMineGenerator(mineCount, width, height),
    private val _blockRows: MutableList<BlockRow> = mutableListOf(),
) {
    val blockRows: List<BlockRow>
        get() = _blockRows.toList()

    val width: Int
        get() = blockRows.first().blocks.size

    val height: Int
        get() = blockRows.size

    init {
        require(width >= MIN_WIDTH) { "너비는 ${MIN_WIDTH}개 이상이어야 합니다." }
        require(height >= MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}개 이상이어야 합니다." }
        require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 ${MIN_MINE_COUNT}개 이상이어야 합니다." }
        require(mineCount <= maxMineCount(width, height)) { "지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다." }

        val mines = mineGenerator.generate()

        MutableList(height) {
            val row = BlockRow(it, width)
            mines.forEach { mine ->
                if (row.contains(mine)) row.find(mine)?.mine()
            }
            row
        }
            .forEach(_blockRows::add)
    }

    fun find(point: Point): Block? = blockRows.find { it.contains(point) }?.find(point)

    companion object {
        const val MIN_MINE_COUNT = 1
        const val MIN_WIDTH = 1
        const val MIN_HEIGHT = 1
    }
}

fun maxMineCount(width: Int, height: Int): Int = width * height
