package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : StringSpec({
    "지뢰찾기 게임 초기화 시 지뢰없는 칸 생성 테스트" {
        // given
        // when
        val mineSweeperGame = MineSweeperGame(3, 4, 1)
        // then
        val expected = arrayListOf(
            arrayListOf(Cell(), Cell(), Cell(), Cell()),
            arrayListOf(Cell(), Cell(), Cell(), Cell()),
            arrayListOf(Cell(), Cell(), Cell(), Cell())
        )
        val actual = mineSweeperGame.cells

        actual shouldBe expected
    }
})
