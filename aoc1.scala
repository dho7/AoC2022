import scala.io.Source
import scala.collection.mutable.ArrayBuffer

@main def computeElfCalories(args: String*): Unit =
    if args.length < 1 then
        println("Please provide Elf Calories file as argument")
    else
        val lines = Source.fromFile(args(0)).getLines.toList
        var maxElfCals:ArrayBuffer[Integer] = new ArrayBuffer[Integer]
        var currElfCals = 0
        for 
            line <- lines
        do
            // Counting elf calories
            if line.length > 0 then
                currElfCals += line.toInt
            // Finished a block of calories, compare versus max
            else
                if maxElfCals.length < 3 then
                    maxElfCals.append(currElfCals)
                else
                    maxElfCals = maxElfCals.sortWith(_ > _)
                    println(maxElfCals.mkString(" "))
                    if currElfCals > maxElfCals(2) then
                        maxElfCals = ArrayBuffer(maxElfCals(0), maxElfCals(1), currElfCals)
                currElfCals = 0
        // Result
        var sum = 0
        maxElfCals.foreach(sum += _)
        println(sum)
