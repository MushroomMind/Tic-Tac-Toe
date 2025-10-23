//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // Initialize empty grid
    val grid = CharArray(9) { '_' }
    var currentPlayer = 'X'
    var gameOver = false

    // Print initial empty grid
    printGrid(grid)

    // Game loop
    while (!gameOver) {
        var validMove = false

        while (!validMove) {
            print("Enter the coordinates for ${if (currentPlayer == 'X') "X" else "O"}: ")
            val input = readln()

            val coordinates : List<Int> = input.split(" ").mapNotNull { it.toIntOrNull() }

            if (coordinates.size != 2) {
                println("You should enter two numbers!")
                continue
            }

            val row = coordinates[0]
            val col = coordinates[1]

            if (row !in 1..3 || col !in 1..3) {
                println("Coordinates should be from 1 to 3!")
                continue
            }

            val index = (row - 1) * 3 + (col - 1)

            if (grid[index] != '_') {
                println("This cell is occupied! Choose another one!")
                continue
            }

            // Valid move - update grid
            grid[index] = currentPlayer
            validMove = true
        }

        // Print updated grid
        printGrid(grid)

        // Check game status
        val status = checkGameStatus(grid)

        when (status) {
            "X wins" -> {
                println("X wins")
                gameOver = true
            }
            "O wins" -> {
                println("O wins")
                gameOver = true
            }
            "Draw" -> {
                println("Draw")
                gameOver = true
            }
            else -> {
                // Continue game, switch player
                currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
            }
        }
    }
}

fun printGrid(grid: CharArray) {
    println("---------")
    for (i in 0..2) {
        print("| ")
        for (j in 0..2) {
            print("${grid[i * 3 + j]} ")
        }
        println("|")
    }
    println("---------")
}

fun checkGameStatus(grid: CharArray): String {
    // Check rows
    for (i in 0..2) {
        if (grid[i * 3] == grid[i * 3 + 1] && grid[i * 3 + 1] == grid[i * 3 + 2] && grid[i * 3] != '_') {
            return "${grid[i * 3]} wins"
        }
    }

    // Check columns
    for (j in 0..2) {
        if (grid[j] == grid[j + 3] && grid[j + 3] == grid[j + 6] && grid[j] != '_') {
            return "${grid[j]} wins"
        }
    }

    // Check diagonals
    if (grid[0] == grid[4] && grid[4] == grid[8] && grid[0] != '_') {
        return "${grid[0]} wins"
    }
    if (grid[2] == grid[4] && grid[4] == grid[6] && grid[2] != '_') {
        return "${grid[2]} wins"
    }

    // Check for draw
    return if (grid.none { it == '_' }) "Draw" else "Game not finished"
}
