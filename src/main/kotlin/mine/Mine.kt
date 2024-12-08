package mine

import cell.Element

data class Mine(
    override val value: String = DEFAULT,
) : Element {
    companion object {
        const val DEFAULT = "*"

        fun ready() = Mine(DEFAULT)
    }
}
