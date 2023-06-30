package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PositiveIntSpec : DescribeSpec(
    {
        describe("생성 검증") {
            context("양수 입력 시") {
                it("정상 생성된다.") {
                    shouldNotThrowAny {
                        PositiveInt(1)
                    }
                }
            }

            context("0 입력 시") {
                it("예외가 발생한다.") {
                    shouldThrow<IllegalArgumentException> {
                        PositiveInt(0)
                    }
                }
            }

            context("음수 입력 시") {
                it("예외가 발생한다.") {
                    shouldThrow<IllegalArgumentException> {
                        PositiveInt(-1)
                    }
                }
            }
        }

        describe("곱하기 연산 검증") {
            withData(
                nameFn = { (operand, result) ->
                    "${operand.first.value} * ${operand.second.value} = ${result.value}"
                },
                ts = listOf(
                    Pair(PositiveInt(1), PositiveInt(10)) to PositiveInt(10),
                    Pair(PositiveInt(2), PositiveInt(10)) to PositiveInt(20),
                ),
            ) { (operand, result) ->
                operand.first * operand.second shouldBe result
            }
        }

        describe("빼기 연산 검증") {
            withData(
                nameFn = { (operand, result) ->
                    "${operand.first.value} - ${operand.second.value} = ${result.value}"
                },
                ts = listOf(
                    Pair(PositiveInt(10), PositiveInt(1)) to PositiveInt(9),
                    Pair(PositiveInt(20), PositiveInt(1)) to PositiveInt(19),
                ),
            ) { (operand, result) ->
                operand.first - operand.second shouldBe result
            }
        }
    },
)
