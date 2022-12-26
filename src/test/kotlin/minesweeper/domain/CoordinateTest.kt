package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import java.lang.IllegalArgumentException

class CoordinateTest : FunSpec({
    context("객체 생성") {
        test("좌표를 가진 객체를 생성한다.") {
            shouldNotThrowAny {
                Coordinate(0, 0)
            }
        }
        test("x / y의 값이 음수일 경우 예외가 발생한다.") {
            shouldThrow<IllegalArgumentException> {
                Coordinate(0, -1)
                Coordinate(-1, 0)
            }
        }
    }
})
