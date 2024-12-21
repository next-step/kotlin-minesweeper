package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CellTest : FunSpec({
    test("지뢰를 갖고 있지 않은 셀을 생성한다.") {
        // given
        val cell = Cell.create(1, 1)

        // when, then
        cell.hasMine shouldBe false
    }

    test("셀을 생성하고 지뢰를 추가한다.") {
        // given
        val cell = Cell.create(1, 1)

        // when
        val res = cell.addMine()

        // then
        res.hasMine shouldBe true
    }
})
