import java.util.*

/*
 *
 * MutableIterable			<--- 		Iterable (covariant)
 *		|									|
 * MutableCollection		<--- 		Collection
 *		|									|
 *		|--> MutableList 	<---			| --> List
 *		|									|
 *		|--> MutableSet 	<---			| --> Set
 *
 *
 *
 * MutableMap				<---		Map
 *
 */

open class Shape
class Rectangle : Shape()
class Circle : Shape()
class Triangle : Shape()

val rectangle = Rectangle()
val circle = Circle()
val triangle = Triangle()

fun printFormatted(prefix: String, other: Any?) = println("$prefix$other")
fun printShapeList(shape: List<Shape>) = printFormatted("ShapeList\t\t\t\t:\t", shape)
fun printShapeMutableList(shape: MutableList<Shape>) = printFormatted("ShapeMutableList\t\t:\t", shape)
fun printShapeSet(shape: Set<Shape>) = printFormatted("ShapeSet\t\t\t\t:\t", shape)
fun printShapeMutableSet(shape: MutableSet<Shape>) = printFormatted("ShapeMutableSet\t\t\t:\t", shape)
fun printMap(map: Map<String, Int>) = printFormatted("Map\t\t\t\t\t:\t", map)

fun main() {
    println("------------- List/Set of Rectangles --------------")
    val rectangleList = listOf(rectangle) // SingletonList
    printShapeList(rectangleList)

    val rectangleMutableList = mutableListOf(rectangle) // ArrayList
    printShapeList(rectangleMutableList)
    printShapeMutableList(rectangleMutableList)

    val rectangleSet = setOf(rectangle, rectangle) // LinkedHashSet
    printShapeSet(rectangleSet)

    val rectangleMutableSet = mutableSetOf(rectangle, rectangle) // LinkedHashSet
    printShapeMutableSet(rectangleMutableSet)

    println("---------------------------------------------------")
    printFormatted("rectangleList\t==\trectangleMutableList?\t", rectangleList == rectangleMutableList)
    printFormatted("rectangleSet\t==\trectangleMutableSet?\t", rectangleSet == rectangleMutableSet)
    printFormatted("rectangleList\t==\trectangleSet?\t\t\t", rectangleList == rectangleSet)
    println("---------------------------------------------------")

    println("---------------- List/Set of Shapes ---------------")
    val shapeList = listOf(rectangle, circle)
    printShapeList(shapeList)

    val shapeMutableList = mutableListOf(rectangle, circle)
    printShapeList(shapeMutableList)
    printShapeMutableList(shapeMutableList)

    val linkedList = LinkedList(listOf(rectangle, circle))
    printShapeList(linkedList)
    printShapeMutableList(linkedList)

    val shapeSet = setOf(rectangle, rectangle, circle, circle)
    printShapeSet(shapeSet)

    val shapeMutableSet = mutableSetOf(rectangle, rectangle, circle, circle)
    printShapeMutableSet(shapeMutableSet)

    println("---------------------------------------------------")
    printFormatted("shapeList\t==\tshapeMutableList?\t", shapeList == shapeMutableList)
    printFormatted("shapeSet\t==\tshapeMutableSet?\t", shapeSet == shapeMutableSet)
    printFormatted("shapeList\t==\tshapeSet?\t\t\t", shapeList == shapeSet)
    println("---------------------------------------------------")

    println("\n---------------- List/Set methods ------------------")
    printFormatted("shapeList\t\t\t\t:\t", shapeList)

    printFormatted("shapeList.first()\t\t:\t", shapeList.first())
    printFormatted("shapeList.last()\t\t:\t", shapeList.last())

    val shuffled = shapeList.shuffled()
    printFormatted("shapeList.shuffled()\t:\t", shuffled)

    val reversed = shapeList.asReversed()
    printFormatted("shapeList.asReversed()\t:\t", reversed)

    val droppedFirst = shapeList.drop(1)
    printFormatted("shapeList.drop(1)\t\t:\t", droppedFirst)

    val droppedLast = shapeList.dropLast(1)
    printFormatted("shapeList.dropLast(1)\t:\t", droppedLast)

    val minus = shapeList - rectangle // or shapeList.minus(rectangle)
    printFormatted("shapeList - rectangle\t:\t", minus)

    val plus = shapeList + rectangle // or shapeList.plus(rectangle)
    printFormatted("shapeList + rectangle\t:\t", plus)

    printFormatted("shapeList\t\t\t\t:\t", shapeList)

    shapeMutableList.add(triangle)
    shapeMutableSet.remove(circle)

    printFormatted("shapeMutableList\t\t:\t", shapeMutableList)
    printFormatted("shapeMutableSet\t\t\t:\t", shapeMutableSet)

    println("\n------------------ List methods -------------------")

    printFormatted("shapeList[0]\t\t\t\t:\t", shapeList[0])
    printFormatted("shapeList.component1()\t\t:\t", shapeList.component1())
    printFormatted("shapeList.firstOrNull()\t\t:\t", shapeList.firstOrNull { it is Triangle })
    printFormatted("shapeList.firstNotNullOf()\t:\t", shapeList.firstNotNullOf { if (it is Circle) triangle else null })

    printFormatted("shapeList.indexOf()\t\t\t:\t", shapeList.indexOf(rectangle))


    println("\n------------------- Set methods -------------------")
    printFormatted("set union\t\t\t\t:\t", shapeSet union shapeMutableSet)
    printFormatted("set intersect\t\t\t:\t", shapeSet intersect shapeMutableSet)
    printFormatted("list union\t\t\t\t:\t", shapeList union shapeMutableList)

    println("\n----------------------- Maps ----------------------")
    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1) //LinkedHashMap
    val mutableMap = mutableMapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3) //LinkedHashMap

    printMap(map)
    printMap(mutableMap)
    printFormatted("map == mutableMap\t?\t", map == mutableMap)

    printFormatted("map[\"key1\"]\t\t\t:\t", map["key1"])
    printFormatted("map[\"key9\"]\t\t\t:\t", map.getOrDefault("key9", 11))

    mutableMap["one"] = 11
    printMap(mutableMap)


    println("\n-------------------- Filtering --------------------")
    printFormatted("shapeList filter\t\t\t:\t", shapeList.filter { it == triangle })
    printFormatted("shapeList filterIsInstance\t:\t", shapeList.filterIsInstance<Triangle>())

    val filterResults = mutableListOf<Shape>()  //destination object
    shapeList.filterTo(filterResults) { it == triangle }
    shapeList.filterIndexedTo(filterResults) { index, _ -> index == 0 }
    printFormatted("shapeList filterResults\t\t:\t", filterResults)

    printFormatted("Map Filter\t\t\t\t\t:\t", map.filter { (key, value) -> key.endsWith("1") && value > 0} )

    val (match, rest) = shapeList.partition { it == circle }
    printFormatted("shapeList partition match\t:\t", match)
    printFormatted("shapeList partition rest\t:\t", rest)

    println("\n------------------- Transforming ------------------")
    printFormatted("Map to bool\t\t\t\t\t:\t", shapeList.map { it == triangle })
    printFormatted("Map indexed\t\t\t\t\t:\t", shapeList.mapIndexed { index, value -> if (value is Triangle) index else -index })

    val mapToList = shapeList.mapTo(mutableListOf()) { it == triangle }
    shapeList.mapTo(mapToList) { it == circle }
    printFormatted("MapTo list\t\t\t\t\t:\t", mapToList)

    printFormatted("Map Keys\t\t\t\t\t:\t", map.mapKeys { it.key.uppercase() })
    printFormatted("Map Values\t\t\t\t\t:\t", map.mapValues { it.value + it.key.length })

    println("\n--------------------- Associate -------------------")
    printFormatted("associateBy\t\t\t\t\t:\t", shapeList.associateBy { it.javaClass.simpleName })
    printFormatted("associateWith\t\t\t\t:\t", shapeList.associateWith { it.javaClass.simpleName })
    printFormatted("associate\t\t\t\t\t:\t", shapeList.associate { it.javaClass.simpleName to (it is Triangle) })

    println("\n---------------------- Ranges ---------------------")
    if (shapeMutableList.size in 1..4) {
        println(shapeMutableList.size)
    }
    for (i in 1..4) print("$i\t")
    println()

    for (i in 4 downTo 1) print("$i\t")
    println()

    for (i in 1..8 step 2) print("$i\t")
    println()

    for (i in 8 downTo 1 step 3) print("$i\t")
    println()

    for (i in 1 until 10) print("$i\t")
    println()

    val numbers = (0..8).toList()
    val numberChunked = numbers.chunked(3)
    printFormatted("numbers\t\t\t\t\t\t:\t", numbers)
    printFormatted("chunked\t\t\t\t\t\t:\t", numberChunked)
    printFormatted("flatten\t\t\t\t\t\t:\t", numberChunked.flatten())
    printFormatted("slice\t\t\t\t\t\t:\t", numbers.slice(0..4 step 2))
    printFormatted("windowed\t\t\t\t\t:\t", numbers.windowed(3))

    println("\n------------------- Sequences ---------------------")
    val numbersSequence = sequenceOf("four", "three", "two", "one")
    val infiniteSequence = generateSequence(1) { it + 2 }
    val oddNumbersLessThan10 = generateSequence(1) { if (it < 8) it + 2 else null }
    val oddNumbers = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    printFormatted("numbersSequence\t\t\t:\t", numbersSequence.take(15).toList())
    printFormatted("infiniteSequence\t\t:\t", infiniteSequence.take(15).toList())
    printFormatted("oddNumbersLessThan10\t:\t", oddNumbersLessThan10.take(15).toList())
    printFormatted("oddNumbers\t\t\t\t:\t", oddNumbers.take(15).toList())


    // Sequence vs Iterable
    val words = "The quick brown fox jumps over the lazy dog".split(" ")

    println("\nIterator")
    val lengthsList = words
        .also { print("filter: ") }
        .filter { print("$it -> "); it.length > 3 }
        .also { print("\nmap: ") }
        .map { print("$it -> "); it.length }
        .take(4)

    println("\nResult: $lengthsList")

    println("\nSequence")
    val lengthsSequence = words.asSequence()
        .filter { print("filter: $it -> "); it.length > 3 }
        .map { print("map: $it \n"); it.length }
        .take(4)

    println("Result: ${lengthsSequence.toList()}")

    val a = fun (s: Int) : Int{
       if(s == 1) {
           1
       }
       return 10
    }
    println(a(1))

    println(a(2))
}


//https://play.kotlinlang.org/
//https://kotlinlang.org/docs/collections-overview.html
//https://kotlinlang.org/docs/generics.html#variance