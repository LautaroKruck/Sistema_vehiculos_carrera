fun main() {
    // Inicialización de vehículos con datos aleatorios y máxima cantidad de combustible
    val vehiculo1 = Automovil("Relámpago", "Mazda", "RX-7", 50f, 50f, 0f, false)
    val vehiculo2 = Automovil("Trueno", "Toyota", "AE86", 40f, 40f, 0f, true)
    val vehiculo3 = Motocicleta("Rayo", "Yamaha", "R1", 18f, 18f, 0f, 998)
    val vehiculo4 = Automovil("Tifón", "Ford", "Mustang", 60f, 60f, 0f, false)
    val vehiculo5 = Motocicleta("Ciclón", "Ducati", "Panigale V4", 17f, 17f, 0f, 1103)
    val vehiculo6 = Automovil("Huracán", "Lamborghini", "Huracán", 85f, 85f, 0f, true)

    // Lista de participantes
    val participantes = listOf(vehiculo1, vehiculo2, vehiculo3, vehiculo4, vehiculo5, vehiculo6)

    // Creación e inicio de la carrera
    val carrera = Carrera("Desafío Supremo", participantes, 1000f)
    carrera.iniciarCarrera()
}
