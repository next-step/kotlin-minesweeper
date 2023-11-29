package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class RowTest : FunSpec(
    {
        test("행에 최소 1개 이상의 좌표가 존재한다") {
            shouldThrow<IllegalArgumentException> { Row() }
        }

        context("행의 좌표 개수는") {
            test("20개로 제한된다") {
                val actual = Row(
                    Coordinate(x = 0, y = 1),
                    Coordinate(x = 1, y = 1),
                    Coordinate(x = 2, y = 1),
                    Coordinate(x = 3, y = 1),
                    Coordinate(x = 4, y = 1),
                    Coordinate(x = 5, y = 1),
                    Coordinate(x = 6, y = 1),
                    Coordinate(x = 7, y = 1),
                    Coordinate(x = 8, y = 1),
                    Coordinate(x = 9, y = 1),
                    Coordinate(x = 10, y = 1),
                    Coordinate(x = 11, y = 1),
                    Coordinate(x = 12, y = 1),
                    Coordinate(x = 13, y = 1),
                    Coordinate(x = 14, y = 1),
                    Coordinate(x = 15, y = 1),
                    Coordinate(x = 16, y = 1),
                    Coordinate(x = 17, y = 1),
                    Coordinate(x = 18, y = 1),
                    Coordinate(x = 19, y = 1)
                )

                actual.size shouldBe 20
            }

            test("20개를 넘으면 예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    Row(
                        Coordinate(x = 0, y = 1),
                        Coordinate(x = 1, y = 1),
                        Coordinate(x = 2, y = 1),
                        Coordinate(x = 3, y = 1),
                        Coordinate(x = 4, y = 1),
                        Coordinate(x = 5, y = 1),
                        Coordinate(x = 6, y = 1),
                        Coordinate(x = 7, y = 1),
                        Coordinate(x = 8, y = 1),
                        Coordinate(x = 9, y = 1),
                        Coordinate(x = 10, y = 1),
                        Coordinate(x = 11, y = 1),
                        Coordinate(x = 12, y = 1),
                        Coordinate(x = 13, y = 1),
                        Coordinate(x = 14, y = 1),
                        Coordinate(x = 15, y = 1),
                        Coordinate(x = 16, y = 1),
                        Coordinate(x = 17, y = 1),
                        Coordinate(x = 18, y = 1),
                        Coordinate(x = 19, y = 1),
                        Coordinate(x = 20, y = 1),
                    )
                }
            }
        }

        context("같은 행에 속하는 좌표들은") {
            test("y 값이 모두 동일해야 한다") {
                shouldNotThrowAny {
                    Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 1), Coordinate(x = 2, y = 1))
                }
            }

            test("y 값이 동일하지 않으면 예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 2))
                }
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

            test("행은 그것에 속한 좌표들의 y값이 클수록 크다") {
                val row1 = Row(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0), Coordinate(x = 2, y = 0))
                val row2 = Row(Coordinate(x = 0, y = 1), Coordinate(x = 1, y = 1), Coordinate(x = 2, y = 1))
                row1 shouldBeLessThan row2
            }
        }

        test("행의 y 좌표 값을 알 수 있다") {
            val row = Row(
                Coordinate(x = 0, y = 1),
                Coordinate(x = 1, y = 1),
                Coordinate(x = 2, y = 1),
                Coordinate(x = 3, y = 1)
            )
            row.getY() shouldBe 1
        }

        test("행에 특정 좌표 묶음을 전달하면 일치하는 좌표에 지뢰를 매설할 수 있다") {
            val row = Row(
                Coordinate(x = 0, y = 1),
                Coordinate(x = 1, y = 1),
                Coordinate(x = 2, y = 1),
                Coordinate(x = 3, y = 1)
            )

            val mines = Coordinates(
                Mine(x = 0, y = 1),
            )

            val actual = row.findAndSetMine(mines).toList()
            actual[0].shouldBeInstanceOf<Mine>()
            actual[1].shouldBeInstanceOf<SafeZone>()
            actual[2].shouldBeInstanceOf<SafeZone>()
            actual[3].shouldBeInstanceOf<SafeZone>()
        }
    }
)
