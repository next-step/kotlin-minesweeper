package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeTypeOf

class BoardTest : FreeSpec({

    val mineFactory = MineFactory.simpleMineFactory(listOf(Coordinate(1, 1)))

    "입력 크기만큼 블럭을 생성한다." {
        val dimension = Dimension(PositiveNumber(10), PositiveNumber(5))
        val board = Board(dimension, mineFactory)
        (1..5).forEach { x ->
            (1..10).forEach { y ->
                board.block(Coordinate(x, y)) shouldNotBe null
            }
        }
    }

    "지뢰 블럭을 생성한다." {
        val dimension = Dimension(PositiveNumber(3), PositiveNumber(3))
        val board = Board(dimension, mineFactory)
        val mineBlock = board.block(Coordinate(1, 1))
        mineBlock.shouldBeTypeOf<Mine>()
    }
})
