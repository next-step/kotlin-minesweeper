package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class LocationTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "0:0",
            "0:1",
            "1:0",
            "10:10",
            "100:1"
        ],
        delimiter = ':'
    )
    fun `생성 성공 확인`(x: Int, y: Int) {
        assertDoesNotThrow { Location(x, y) }
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "-1:0",
            "-1:-1",
            "1:-1",
            "-10:-10"
        ],
        delimiter = ':'
    )
    fun `생성 실패 확인`(x: Int, y: Int) {
        assertThrows<IllegalArgumentException>("입력 좌표가 0 이상이어야 합니다.") {
            Location(x, y)
        }
    }

    @Test
    fun `pick 메서드 호출전 후 isMine 프로퍼티 확인`() {
        val sampleLocation = Location(1, 2)

        assertThat(sampleLocation.isMine).isFalse

        sampleLocation.pick()

        assertThat(sampleLocation.isMine).isTrue
    }

    @Test
    fun `pick 두번 호출시 예외 발생 확인`() {
        val sampleLocation = Location(1, 2)

        sampleLocation.pick()

        assertThrows<IllegalStateException>("이미 지뢰로 선택된 위치입니다.") {
            sampleLocation.pick()
        }
    }

    @Test
    fun `지뢰의 위험도 증가시 예외 발생 확인`() {
        val sampleLocation = Location(1, 2).apply { pick() }

        assertThrows<IllegalStateException>("지뢰에는 위험도를 증가시킬 수 없습니다.") {
            sampleLocation.increaseRisk()
        }
    }

    @ParameterizedTest
    @MethodSource("pairAndArounds")
    fun `주변 xy 쌍 반환 확인`(pair: Pair<Int, Int>, expected: List<Pair<Int, Int>>) {
        val (x, y) = pair
        val location = Location(x, y)

        assertThat(location.aroundPairs).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun pairAndArounds() = listOf(
            Arguments.of(
                0 to 0,
                listOf(
                    -1 to -1,
                    -1 to 0,
                    -1 to 1,
                    0 to -1,
                    0 to 1,
                    1 to -1,
                    1 to 0,
                    1 to 1
                )
            ),
            Arguments.of(
                4 to 2,
                listOf(
                    3 to 1,
                    3 to 2,
                    3 to 3,
                    4 to 1,
                    4 to 3,
                    5 to 1,
                    5 to 2,
                    5 to 3
                )
            )
        )
    }
}
