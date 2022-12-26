package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import java.lang.IllegalArgumentException

class MapSizeTest : FunSpec({
    context("객체 생성") {
        test("높이, 너비를 입력받아 객체를 생성한다.") {
            shouldNotThrowAny {
                MapSize(1, 1)
            }
        }
        test("높이 & 너비가 모두 1이하일 경우 예외가 발생한다..") {
            shouldThrow<IllegalArgumentException> {
                MapSize(1, 0)
                MapSize(0, 1)
            }
        }
    }
})
