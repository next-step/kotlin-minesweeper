package step4.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import step4.domain.MinesweeperGame.Companion.createNewGame
import step4.domain.state.Ready

class MinesweeperGameTest : FunSpec({

    context("createNewGame") {
        test("높이와 너비를 받아 새 게임을 생성한다.") {
            val actual = createNewGame(10, 10)

            actual.height shouldBe 10
            val actualState = actual.state.shouldBeTypeOf<Ready>()
            actualState.cells.values shouldHaveSize 100
        }
    }
})
