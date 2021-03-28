/*
 * Copyright (c) 2021. LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package domain


/**
 * @author tae-heon.song<taeheon.song@linecorp.com>
 * @since 2021. 03. 28.
 */
class Mines(private val values: List<Mine>) : List<Mine> by values {
    fun isMine(row: Int, col: Int): Boolean {
        return values.any { it.position.row == row && it.position.col == col }
    }
}