package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({

    test("지뢰판 사이즈보다 많은 지뢰 개수가 들어올 경우 예외가 발생한다.") {
        // given
        val boardSize = BoardSize(5, 5)
        val mineCount = 100

        // when, then
        shouldThrow<IllegalArgumentException> { MineBoard(boardSize, mineCount) }
            .also { it.message shouldBe "지뢰판의 크기보다 지뢰의 개수가 더 많습니다. [지뢰 개수: $mineCount]" }
    }

})
