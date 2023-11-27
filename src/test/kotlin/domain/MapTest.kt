package domain

import domain.builder.map
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MapTest : FunSpec({
    context("맵에 속한 행들은") {
        test("크기가 모두 동일해야 한다") {
            shouldNotThrowAny {
                Map(
                    Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0)),
                    Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 1), Coordinate(x = 2, y = 1)),
                    Row(Coordinate(x = 0, y = 2), Coordinate(x = 1, y = 2), Coordinate(x = 2, y = 2)),
                )
            }
        }

        test("크기가 다르다면 예외가 발생한다") {
            shouldThrow<IllegalArgumentException> {
                Map(
                    Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0)),
                    Row(Coordinate(x = 0, y = 1)),
                    Row(Coordinate(x = 0, y = 2), Coordinate(x = 1, y = 2), Coordinate(x = 2, y = 2)),
                )
            }
        }

        test("맵의 행들의 y값은 0부터 시작하지 않으면 예외가 발생한다") {
            shouldThrow<IllegalArgumentException> {
                Map(
                    Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 1), Coordinate(x = 2, y = 1)),
                    Row(Coordinate(x = 0, y = 2), Coordinate(x = 1, y = 2), Coordinate(x = 2, y = 2)),
                    Row(Coordinate(x = 0, y = 3), Coordinate(x = 1, y = 3), Coordinate(x = 2, y = 3)),
                )
            }
        }

        test("맵에 최소 1개 이상의 행이 존재하지 않으면 예외가 발생한다") {
            shouldThrow<IllegalArgumentException> { Map() }
        }


        test("맵의 행들의 y값이 1씩 커지지 않으면 예외가 발생한다") {
            shouldThrow<IllegalArgumentException> {
                Map(
                    Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0)),
                    Row(Coordinate(x = 0, y = 2), Coordinate(x = 1, y = 2), Coordinate(x = 2, y = 2)),
                    Row(Coordinate(x = 0, y = 3), Coordinate(x = 1, y = 3), Coordinate(x = 2, y = 3)),
                )
            }
        }

        test("맵에 y 값이 동일한 행이 두 개 이상 포함될 수 없다") {
            Map(
                Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0)),
                Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0)),
            ).size shouldBe 1
        }

        test("MapBuilder 테스트") {
            val actual = map {
                row {
                    col {
                        coordinate(y = 0, x = 0)
                        coordinate(y = 0, x = 1)
                        coordinate(y = 0, x = 2)
                    }
                }
            }

            actual.rows.first().columns.size shouldBe 3
        }
    }
})
