package cinema

val rowAndSeatNumber =  mutableListOf<String>()
var numberOfTickets = 0
var currentIncome = 0
var totalIncome = 0
var seatNumber = 0
var rowNumber = 0
var count = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    while (true) {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when (readln().toInt()) {
            1 -> showSeats(rows, seats)
            2 -> getRowSeatNumber(rows, seats)
            3 -> getStatistics(rows, seats)
            0 -> return
            else -> println("wrong input")
        }
        count++
    }
}

fun showSeats(rows: Int, seats: Int) {
    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }
    if (count == 0) {

    } else {
        for (i in rowAndSeatNumber) {
            rowNumber = i[0].digitToInt()
            seatNumber = i[1].digitToInt()

            cinema[rowNumber - 1][seatNumber - 1] = 'B'
        }
    }
    print("\nCinema:\n  ")
    for (i in 1..seats) print("$i ")
    for (i in cinema.indices) print("\n${i + 1} ${cinema[i].joinToString(" ")}")
    println("")
}

fun getRowSeatNumber(rows: Int, seats: Int) {
    while (true) {
        println("\nEnter a row number:")
        rowNumber = readln().toInt()
        println("Enter a seat number in that row:")
        seatNumber = readln().toInt()
        if (rowNumber <= rows && seatNumber <= seats) {
            if (rowAndSeatNumber.contains(rowNumber.toString() + seatNumber.toString())) {
                println("That ticket has already been purchased!")
            } else {
                rowAndSeatNumber.add(rowNumber.toString() + seatNumber.toString())
                val cost = if (rows * seats <= 60) 10 else if (rowNumber > rows / 2) 8 else 10
                currentIncome+= cost
                numberOfTickets++
                println("Ticket price: \$$cost")
                break
            }
        } else {
            println("Wrong input!")
        }
    }
}

fun getStatistics(rows: Int, seats: Int) {
    totalIncome = if (rows * seats <= 60) {
        rows * seats * 10
    } else {
        (rows / 2) * seats * 10 + (rows - (rows / 2)) * seats * 8
    }
    println("\nNumber of purchased tickets: $numberOfTickets")
    if (currentIncome == 0) {
        println("Percentage: 0.00%")
    } else {
        val percentage = (numberOfTickets * 100 / (rows * seats).toFloat())
        println("Percentage: ${"%.2f".format(percentage)}%")
    }
    println("Current income: \$$currentIncome")
    println("Total income: \$$totalIncome")
}