package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec

class BoardTest : ShouldSpec({
    context("5 by 5 크기의 지도를 생성 하려고 한다.") {
        val height = 5
        val width = 5
        should("지뢰 숫자가 지도 크기(25)보다 크면 지도를 생성할 수 없다.") {
            shouldThrow<RuntimeException> { Board.of(width = width, height = height, countOfMine = 26) }
        }
        should("지뢰 숫자가 1개 보다 적으면 지도를 생성할 수 없다.") {
            shouldThrow<RuntimeException> { Board.of(width = width, height = height, countOfMine = 0) }
            shouldThrow<RuntimeException> { Board.of(width = width, height = height, countOfMine = -1) }
        }
        should("지뢰 숫자가 적정 범위(1개 이상 - 지도 크기 이하)일 경우 지도를 생성할 수 있다.") {
            (1..25).forEach { countOfMine ->
                shouldNotThrow<RuntimeException> { Board.of(width = width, height = height, countOfMine = countOfMine) }
            }
        }
    }

    context("5 by 5 크기에 3개의 지뢰를 갖는 지도를 생성 한다.") {
        val board = Board.of(width = 5, height = 4, countOfMine = 3)
        should("지도 크기 범위 이내의 포인트에 대해 셀을 가져올 수 있다.") {
            shouldNotThrow<RuntimeException> { board.cell(0, 0) }
            shouldNotThrow<RuntimeException> { board.cell(4, 3) }
        }
        should("지도 크기 범위 밖의 포인트로 셀을 가져올 수 없다.") {
            shouldThrow<RuntimeException> { board.cell(5, 4) }
        }
    }
})
