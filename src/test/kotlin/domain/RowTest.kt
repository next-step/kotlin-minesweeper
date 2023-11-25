package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RowTest : FunSpec(
    {
        test("행에 최소 1개 이상의 좌표가 존재한다") {
            shouldThrow<IllegalArgumentException> { Row() }
        }

        test("같은 행에 속하는 좌표들은 y 값이 모두 동일해야 한다") {
            shouldThrow<IllegalArgumentException> {
                Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 2))
            }
        }

        test("행에 x, y 값이 동일한 좌표가 두 개 이상 포함될 수 없다") {
            Row(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = 0)).size shouldBe 1
        }

        context("행의 좌표들의 x값은") {
            test("1씩 커져야 한다") {
                shouldNotThrowAny {
                    Row(
                        Coordinate(x = 0, y = 1),
                        Coordinate(x = 1, y = 1),
                        Coordinate(x = 2, y = 1),
                        Coordinate(x = 3, y = 1)
                    )
                }
            }
            test("1씩 커지지 않으면 예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    Row(
                        Coordinate(x = 0, y = 1),
                        Coordinate(x = 1, y = 1),
                        Coordinate(x = 2, y = 1),
                        Coordinate(x = 4, y = 1)
                    )
                }
            }

            test("0부터 시작해야 한다") {
                shouldNotThrowAny {
                    Row(
                        Coordinate(x = 0, y = 1),
                        Coordinate(x = 1, y = 1),
                        Coordinate(x = 2, y = 1),
                        Coordinate(x = 3, y = 1)
                    )
                }
            }

            test("0부터 시작하지 않으면 예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    Row(
                        Coordinate(x = 1, y = 1),
                        Coordinate(x = 2, y = 1),
                        Coordinate(x = 3, y = 1),
                        Coordinate(x = 4, y = 1)
                    )
                }
            }
        }
    }
)
