package domain

import domain.MapElement.Companion.NON_MINE_CHAR

object NonMine : MapElement {
    override val character = NON_MINE_CHAR

    override fun toString(): String {
        return character
    }
}
