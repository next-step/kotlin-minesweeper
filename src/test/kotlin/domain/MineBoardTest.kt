package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({
    test("지뢰찾기 보드를 만든다.") {
        val mineBoard = MineBoard.init(10, 10)

        mineBoard.size shouldBe 10
        mineBoard.forEach { row ->
            row.size shouldBe 10
        }
    }
})
