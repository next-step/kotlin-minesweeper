package minesweeper.domain

import minesweeper.model.Point

class BlockRow(
    val blocks: List<Block>,
) {

    val length: Int
        get() = blocks.size

    init {
        require(blocks.isNotEmpty()) { "BlockRow는 최소 1개의 Block을 가져야 합니다." }
    }

    constructor(height: Int, width: Int) : this(
        MutableList(width) { Block(height, it) }
    ) {
        require(width >= 0) { "BlockRow의 width는 0 이상이어야 합니다." }
        require(height >= 0) { "BlockRow의 height는 0 이상이어야 합니다." }
    }

    fun contains(point: Point): Boolean = blocks.any { it.point == point }

    fun find(point: Point): Block? = blocks.find { it.point == point }

    override fun toString(): String = blocks.joinToString(" ")
}
