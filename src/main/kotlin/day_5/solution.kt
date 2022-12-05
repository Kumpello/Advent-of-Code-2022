package day_5

import java.io.File

const val fileName = "src/main/resources/day_5/input"
var stacksOfCargo: MutableList<MutableList<Char>> = mutableListOf()
var lineWithStackNumbers = 0

fun main(args: Array<String>) {
    println(getTopCrates())
}

fun getTopCrates() {
    stacksOfCargo = getStacks()
    val lines = readFileAsLinesUsingUseLines(fileName)
    lines.forEach { line ->
        if (line.isNotEmpty() && line.toCharArray().get(0) == 'm') {
            /*
            CRANE 9000
            val values = line.split(' ')
            var howMany: Int = values.get(1).toInt()
            var from: Int = values.get(3).toInt() - 1
            var to: Int = values.get(5).toInt() - 1
            var listToAdd: MutableList<Char> = mutableListOf()
            for (i in 1..howMany) {
                println(stacksOfCargo[from])
                listToAdd.add(stacksOfCargo[from].last())
                stacksOfCargo[from].removeLast()
            }
            for (i in 0..howMany - 1) {
                stacksOfCargo[to].add(listToAdd[i])
            }*/
            val values = line.split(' ')
            var howMany: Int = values.get(1).toInt()
            var from: Int = values.get(3).toInt() - 1
            var to: Int = values.get(5).toInt() - 1
            var listToAdd: MutableList<Char> = mutableListOf()
            for (i in 1..howMany) {
                println(stacksOfCargo[from])
                listToAdd.add(stacksOfCargo[from].last())
                stacksOfCargo[from].removeLast()
            }
            listToAdd.reverse()
            for (i in 0..howMany - 1) {
                stacksOfCargo[to].add(listToAdd[i])
            }
        }
    }
    stacksOfCargo.forEach { stack ->
        print(stack.last())
    }
}

fun getStacks(): MutableList<MutableList<Char>> {
    var end = false
    var numberOfCollumns = 0
    val lines = readFileAsLinesUsingUseLines(fileName)
    lines.forEach { line ->
        if (line.isNotEmpty() && line.toCharArray()[1].equals('1')) {
            val numbers = line.split("  ")
            val size = numbers.size
            numberOfCollumns = numbers[size - 1].trim().toInt()
        }
    }
    var stacks: MutableList<MutableList<Char>> = mutableListOf()
    for (i in 0..numberOfCollumns - 1) {
        stacks.add(i, mutableListOf())
    }
    lines.forEach { line ->
        if (line.isNotEmpty() && line[1].equals('1')) {
            end = true
        }
        if (!end) {
            var iterator = 1;
            line.toCharArray()
            for (i in 0..numberOfCollumns - 1) {
                val character = line.toCharArray()[iterator]
                if (character != ' ') {
                    stacks[i].add(character)
                }
                iterator += 4
            }
        }
    }
    for (i in 0..numberOfCollumns - 1) {
        stacks[i].reverse()
    }
    return stacks
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
