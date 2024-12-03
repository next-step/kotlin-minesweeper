package mine

import cell.Element

data object Mine : Element {
    private const val DEFAULT = '*'
    private const val VALUE: Char = DEFAULT

    override val value: Char
        get() = VALUE
}
