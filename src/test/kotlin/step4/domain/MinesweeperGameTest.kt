package step4.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeTypeOf
import step4.domain.MinesweeperGame.Companion.createNewGame
import step4.domain.state.Ready

class MinesweeperGameTest : FunSpec({

    context("init") {
        test("높이가 0이하인 경우 예외 추가 및 테스트") {
            val exception = shouldThrowExactly<IllegalArgumentException> { MinesweeperGame(0, Ready(1, Cells())) }
            exception shouldHaveMessage "높이는 0보다 커야합니다."
        }
    }

    context("createNewGame") {
        test("높이와 너비를 받아 새 게임을 생성한다.") {
            val actual = createNewGame(10, 10)

            actual.height shouldBe 10
            val actualState = actual.state.shouldBeTypeOf<Ready>()
            actualState.cells.values shouldHaveSize 100
        }
    }
})
