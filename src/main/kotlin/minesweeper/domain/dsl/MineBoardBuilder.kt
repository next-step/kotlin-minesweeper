package minesweeper.domain.dsl

import minesweeper.domain.MineBoard

@BuilderMarker
class MineBoardBuilder : Builder<MineBoard> {
    override fun build(): MineBoard {
        TODO("Not yet implemented")
    }
}

fun buildMineBoard(block: MineBoardBuilder.() -> Unit): MineBoard = MineBoardBuilder().apply(block).build()
