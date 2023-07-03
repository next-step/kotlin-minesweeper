package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec

class BoardTest : ShouldSpec({
    context("5 by 5 크기의 지도를 생성 하려고 한다.") {
        val height = 5
        val width = 5
        val board: (countOfMine: Int) -> Board = { countOfMine -> Board.of(width = width, height = height, countOfMine = countOfMine) }
        should("지뢰 숫자가 지도 크기(25)보다 크면 지도를 생성할 수 없다.") {
            shouldThrow<RuntimeException> { board(26) }
        }
        should("지뢰 숫자가 1개 보다 적으면 지도를 생성할 수 없다.") {
            shouldThrow<IllegalArgumentException> { board(0) }
            shouldThrow<IllegalArgumentException> { board(-1) }
        }
        should("지뢰 숫자가 적정 범위(1개 이상 - 지도 크기 이하)일 경우 지도를 생성할 수 있다.") {
            (1..25).forEach { countOfMine ->
                shouldNotThrow<IllegalArgumentException> { board(countOfMine) }
            }
        }
    }
})
