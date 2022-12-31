package minesweeper.domain

import minesweeper.model.Point

class BlockRow(
    val blocks: List<Block>,
) {
    constructor(height: Int, width: Int) : this(
        MutableList(width) { Block(height, it) }
    )

    val length: Int
        get() = blocks.size

    init {
        require(blocks.isNotEmpty()) { "BlockRow는 최소 1개의 Block을 가져야 합니다." }
    }

    fun contains(point: Point): Boolean = blocks.any { it.point == point }

    fun find(point: Point): Block? = blocks.find { it.point == point }

    override fun toString(): String = blocks.joinToString(" ")
}
