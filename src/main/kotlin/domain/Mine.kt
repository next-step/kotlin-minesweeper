package domain

import domain.MapElement.Companion.MINE_CHAR

object Mine : MapElement {
    override val character = MINE_CHAR

    override fun toString(): String {
        return character
    }
}
