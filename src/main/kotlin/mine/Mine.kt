package mine

import cell.Element

data class Mine(
    override val value: Char = DEFAULT,
) : Element {
    companion object {
        private const val DEFAULT = '*'

        fun ready() = Mine(DEFAULT)
    }
}
