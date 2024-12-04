package cell

data object Cell : Element {
    private const val DEFAULT = 'C'
    private const val VALUE: Char = DEFAULT

    override val value: Char
        get() = VALUE
}
