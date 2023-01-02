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

    constructor(xIndex: Int, length: Int, block: (Point) -> Int = { 0 }) : this(
        MutableList(length) { Block(Point(xIndex, it), block) }
    ) {
        require(xIndex >= MIN_VALUE) { "x 좌표는 $MIN_VALUE 이상이어야 합니다." }
        require(length >= MIN_VALUE) { "BlockRow 길이는 $MIN_VALUE 이상이어야 합니다." }
    }

    fun contains(point: Point): Boolean = blocks.any { it.point == point }

    fun find(point: Point): Block? = blocks.find { it.point == point }

    fun allOpen(): Boolean = blocks.all { !it.state.isMine() && it.state.isOpen() }

    override fun toString(): String = blocks.joinToString(" ")

    companion object {
        private const val MIN_VALUE = 0
    }
}
