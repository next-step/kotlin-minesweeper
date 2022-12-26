package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SiteTest : FunSpec({
    context("객체 생성") {
        test("좌표를 입력받아 객체를 생성한다.") {
            shouldNotThrowAny {
                Site(1, 2)
            }
        }
    }
    context("mine()") {
        test("지뢰를 심는다.") {
            val site = Site(1, 2)

            val actual = site.mine()

            site.hasMine shouldBe false
            actual.hasMine shouldBe true
        }
    }
})
