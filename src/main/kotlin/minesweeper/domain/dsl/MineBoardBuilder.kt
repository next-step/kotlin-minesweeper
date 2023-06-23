package minesweeper.domain.dsl

import minesweeper.domain.MineBoard
import minesweeper.request.MinesCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

@BuilderMarker
class MineBoardBuilder : Builder<MineBoard> {
    private var width: Int? = null
    private var height: Int? = null
    private var mineCapacity: Int? = null
    private var mineBoardCreateStrategy: MineBoardCreateStrategy? = null

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun mineCapacity(value: Int) {
        mineCapacity = value
    }

    fun strategy(strategy: MineBoardCreateStrategy) {
        mineBoardCreateStrategy = strategy
    }

    override fun build(): MineBoard = mineBoardCreateStrategy?.create(
        MinesCreateRequest(
            height = height ?: throw IllegalArgumentException("높이 정보가 없습니다."),
            width = width ?: throw IllegalArgumentException("너비 정보가 없습니다."),
            mineCapacity = mineCapacity ?: throw IllegalArgumentException("지뢰 갯수 정보가 없습니다.")
        )
    ) ?: throw IllegalArgumentException("지뢰 찾기 보드 생성기가 등록되지 않았습니다.")
}

fun buildMineBoard(block: MineBoardBuilder.() -> Unit): MineBoard = MineBoardBuilder().apply(block).build()
