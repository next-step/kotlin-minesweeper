package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BoardTest : FunSpec({
    context("높이와 너비만큼의 게임판을 생성한다.") {
        val board = Board(10, 15, 10)
        board.width shouldBe 10
        board.height shouldBe 15
    }
})
