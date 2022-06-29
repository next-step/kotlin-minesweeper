package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineBoardMetaTest {
    @ParameterizedTest
    @CsvSource(
        value = ["0:2", "3:0", "-1:1", "10:-2"],
        delimiter = ':'
    )
    fun `0 이하의 높이 너비 입력시 예외 발생`(rowCount: Int, columnCount: Int) {
        assertThrows<IllegalArgumentException>(
            "높이와 너비 모두 0보다 커야합니다. (높이:$rowCount, 너비:$columnCount)"
        ) { MineBoardMeta(rowCount, columnCount) }
    }

    @ParameterizedTest
    @CsvSource(
        value = ["1:2:2", "3:2:6", "5:5:25"],
        delimiter = ':'
    )
    fun `높이 너비 입력시 전체 면적 갯수 확인`(
        rowCount: Int,
        columnCount: Int,
        total: Int
    ) {
        assertAll(
            {
                assertThat(
                    MineBoardMeta(rowCount, columnCount).totalArea
                ).isEqualTo(total)
            }
        )
    }
}
