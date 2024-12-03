package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BoardTest : StringSpec({
    "보드의 높이, 너비는 양수 값이어야 합니다." {
        listOf(
            0 to 0,
            0 to 1,
            1 to 0,
            -1 to 1,
            1 to -1,
            -1 to -1
        ).forEach { (height, width) ->
            shouldThrow<IllegalArgumentException> { Board(height, width) }
        }
    }

    "보드의 높이 * 너비를 가진 정수 배열을 생성한다." {
        listOf(
            1 to 1,
            5 to 30,
            10 to 10
        ).forEach { (height, width) ->
            val board = Board(height, width)
            board.points.size shouldBe height
            board.points[0].size shouldBe width
        }
    }
})
