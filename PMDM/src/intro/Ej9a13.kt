package intro

    fun main(){

         ej9()
         ej10()
         ej11()
         ej12()
         ej13()

    }

    fun ej9() {
        // Ejercicio 9: Condicional simple
        val nota = 6

        if (nota >= 5) {
            println("Aprobado")
        } else {
            println("Suspenso")
        }
    }

    fun ej10() {
        // Ejercicio 10: Condicional múltiple con when
        print("Introduce un número del 1 al 7: ")
        val numero = readLine()?.toIntOrNull()

        when (numero) {
            1 -> println("Lunes")
            2 -> println("Martes")
            3 -> println("Miércoles")
            4 -> println("Jueves")
            5 -> println("Viernes")
            6 -> println("Sábado")
            7 -> println("Domingo")
            else -> println("Número fuera de rango")
        }
    }

    fun ej11() {
        // Ejercicio 11: Bucle for
        println("Números del 1 al 10:")
        for (i in 1..10) {
            println(i)
        }

        println("\nNúmeros pares del 1 al 20:")
        for (i in 1..20) {
            if (i % 2 == 0) {
                println(i)
            }
        }
    }

    fun ej12() {
        // Ejercicio 12: Bucle while
        print("Introduce un número para la cuenta regresiva: ")
        var numero = readLine()?.toIntOrNull() ?: 0

        while (numero >= 0) {
            println(numero)
            numero--
        }
    }

    fun esMayorDeEdad(edad: Int): Boolean {
        return edad >= 18
    }

    fun ej13() {
        // Ejercicio 13: Mini programa combinado
        var mayores = 0

        for (i in 1..5) {
            print("Introduce la edad de la persona $i: ")
            val edad = readLine()?.toIntOrNull() ?: 0

            if (esMayorDeEdad(edad)) {
                mayores++
            }
        }

        println("Número de personas mayores de edad: $mayores")
    }


