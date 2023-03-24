fun <T, R> joinList(list: List<T>, initial: R, operation: (R, T) -> R): R {
    var accumulator = initial
    for (element in list) accumulator = operation(accumulator, element)
    return accumulator
}

fun concatStringOperator(accumulator: String, element: Int): String = "$accumulator $element"

val plusOperator = { accumulator: Int, element: Int -> accumulator + element }

val timesOperator: (Int, Int) -> Int = { accumulator: Int, element: Int -> accumulator * element }

val intPlus: Int.(Int) -> Int = Int::plus // extension-like call

fun main() {
    val items = listOf(1, 2, 3, 4, 5)
    println(joinList(items, 0) { accumulator: Int, element: Int -> accumulator - element })
    println(joinList(items, 1, fun(accumulator: Int, _: Int) = accumulator))
    println(joinList(items, "Elements:", ::concatStringOperator))
    println(joinList(items, 1, Int::times))
    println(joinList(items, 0, plusOperator))

    println(timesOperator.invoke(5, 6))
    println(timesOperator(5, 6))
    println(2.intPlus(3))

}