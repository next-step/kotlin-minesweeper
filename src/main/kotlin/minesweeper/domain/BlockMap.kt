package minesweeper.domain

import minesweeper.model.BlockOpenResult
import minesweeper.model.BlockOpenResult.ALREADY_OPENED
import minesweeper.model.BlockOpenResult.MINE
import minesweeper.model.BlockOpenResult.OPENED
import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width

class BlockMap(
    height: Height,
    width: Width,
    mineCount: MineCount,
    mineGenerator: MineGenerator = RandomMineGenerator(),
    mineDetector: MineDetector = BlockMineDetector(width, height),
    private val _blockRows: MutableList<BlockRow> = mutableListOf(),
) {
    val blocks: List<BlockRow>
        get() = _blockRows.toList()

    val width: Int
        get() = blocks.first().length

    val height: Int
        get() = blocks.size

    init {
        val mines = mineGenerator.generate(mineCount, height, width)

        val aa = mutableListOf<Int>()
        aa.add(2)

        MutableList(height.value) {
            val row = BlockRow(it, width.value) { point ->
                mineDetector.detect(point, mines)
            }
            mines.forEach { mine ->
                if (row.contains(mine)) row.find(mine)?.mine()
            }
            row
        }
            .forEach(_blockRows::add)
    }

    constructor(width: Int, height: Int, mineCount: Int) : this(
        Height(height),
        Width(width),
        MineCount(mineCount),
    )

    fun find(point: Point): Block? = blocks.find { it.contains(point) }?.find(point)

    fun open(point: Point): BlockOpenResult {
        val block = find(point)
        require(block != null) { "유효하지 않은 블록 위치 입니다." }
        if (!block.isMine() && block.isOpen()) {
            return ALREADY_OPENED
        }
        if (block.isMine()) return MINE
        openAroundBlock(block)
        return OPENED
    }

    fun countOpenBlocks(): Int =
        blocks.sumOf { row -> row.blocks.count { !it.isMine() && it.isOpen() } }

    private fun openAroundBlock(block: Block) {
        val countOfSurroundingMines = block.state.countOfSurroundMines
        if (block.isMine() || block.isOpen()) return
        block.open()
        if (countOfSurroundingMines == 0) {
            val neighbors = PointDirections.neighbors(point = block.point, maxPoint = Point(width, height))
            neighbors.forEach { neighbor ->
                val neighborBlock = find(neighbor)
                if (neighborBlock != null && !neighborBlock.state.isMine()) {
                    openAroundBlock(neighborBlock)
                }
            }
        }
    }

    fun open(pointX: Int, pointY: Int): BlockOpenResult = open(Point(pointX, pointY))
    fun allOpen(): Boolean = blocks.all { it.allOpen() }
}
