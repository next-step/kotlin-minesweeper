package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellTest : StringSpec({
    "이름을 C 로 같는 셀 객체를 생성한다" {
        // when
        val cell = Cell()

        // then
        cell.name shouldBe "C"
    }

    "셀의 이름을 * 로 변경할 수 있다" {
        // given
        val cell = Cell()

        // when
        cell.change("*")

        // then
        cell.name shouldBe "*"
    }
})
