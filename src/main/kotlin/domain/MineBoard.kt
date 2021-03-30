package domain

import domain.block.Block

data class MineBoard(val width: Int, private val height: Int, val value: Map<Coordinate, Block>)
