import kotlin.random.Random

/**
 * Representa una carrera automovilística que incluye múltiples vehículos como participantes.
 * La carrera se lleva a cabo hasta que al menos uno de los vehículos alcanza o supera la distancia total establecida.
 *
 * @property nombreCarrera El nombre identificador de la carrera.
 * @property participantes La lista de vehículos que participan en la carrera.
 * @property distanciaTotal La distancia total que los vehículos deben alcanzar para completar la carrera.
 */
class Carrera(
    val nombreCarrera: String,
    var participantes: List<Vehiculo>,
    val distanciaTotal: Float
) {
    // Registro de todas las acciones realizadas por cada vehículo durante la carrera.
    var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()

    // Contador de las veces que cada vehículo ha repostado durante la carrera.
    var paradasRepostaje: MutableMap<String, Int> = mutableMapOf()

    init {
        // Inicialización de registros para cada vehículo participante.
        participantes.forEach { vehiculo ->
            historialAcciones[vehiculo.nombre] = mutableListOf("Inicio de la carrera")
            paradasRepostaje[vehiculo.nombre] = 0
        }
    }

    /**
     * Inicia la ejecución de la carrera, avanzando aleatoriamente los vehículos hasta que uno alcanza la distancia total.
     * Registra todas las acciones realizadas y muestra los resultados finales, incluyendo el ganador de la carrera.
     */
    fun iniciarCarrera() {
        println("La carrera $nombreCarrera ha comenzado!")

        while (!participantes.any { it.kilometrosActuales >= distanciaTotal }) {
            val vehiculo = participantes.random()
            avanzarVehiculo(vehiculo)
        }

        println("La carrera $nombreCarrera ha finalizado.")
        println("")
        mostrarGanador()
        mostrarResultadosFinales(obtenerResultados())
    }

    /**
     * Muestra el ganador de la carrera, determinado por el vehículo que haya alcanzado la mayor distancia.
     */
    private fun mostrarGanador() {
        val ganador = participantes.maxByOrNull { it.kilometrosActuales }
        ganador?.let {
            println("Ha ganado ${ganador.nombre} con ${ganador.kilometrosActuales} kilómetros recorridos.\n")
        }
    }

    /**
     * Avanza un vehículo una distancia aleatoria y registra la acción.
     *
     * @param vehiculo El vehículo a avanzar.
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo) {
        val distancia = Random.nextInt(10, 200).toFloat()
        vehiculo.kilometrosActuales += distancia
        registrarAccion(vehiculo.nombre, "Avanzó $distancia km")
    }

    /**
     * Registra una acción realizada por un vehículo en su historial de acciones.
     *
     * @param vehiculoNombre El nombre del vehículo que realiza la acción.
     * @param accion La descripción de la acción realizada.
     */
    private fun registrarAccion(vehiculoNombre: String, accion: String) {
        historialAcciones.getOrPut(vehiculoNombre) { mutableListOf() }.add(accion)
    }

    /**
     * Obtiene y prepara los resultados finales de la carrera, ordenando los vehículos por la distancia recorrida en orden descendente.
     *
     * @return Una lista de objetos ResultadoCarrera con los resultados finales.
     */
    fun obtenerResultados(): List<ResultadoCarrera> {
        return participantes.sortedByDescending { it.kilometrosActuales }
            .map { vehiculo ->
                ResultadoCarrera(
                    vehiculo = vehiculo,
                    kilometraje = vehiculo.kilometrosActuales,
                    paradasRepostaje = paradasRepostaje[vehiculo.nombre] ?: 0,
                    historialAcciones = historialAcciones[vehiculo.nombre] ?: mutableListOf()
                )
            }
    }

    /**
     * Muestra los resultados finales de cada vehículo participante en la carrera.
     *
     * @param resultados La lista de resultados finales a mostrar.
     */
    private fun mostrarResultadosFinales(resultados: List<ResultadoCarrera>) {
        resultados.forEach { resultado ->
            println("Vehículo: ${resultado.vehiculo.nombre}")
            println("Kilometraje: ${resultado.kilometraje}")
            println("Acciones: ${resultado.historialAcciones.joinToString(", ")}")
            println()
        }
    }
}
