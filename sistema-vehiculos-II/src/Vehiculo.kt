/**
 * Clase base para representar un vehículo genérico. Contiene propiedades y métodos comunes
 * que serían aplicables a cualquier tipo de vehículo.
 *
 * @property nombre El nombre identificador del vehículo.
 * @property marca La marca del vehículo.
 * @property modelo El modelo específico del vehículo.
 * @property capacidadCombustible La capacidad total de combustible del vehículo en litros.
 * @property combustibleActual La cantidad actual de combustible en el vehículo en litros.
 * @property kilometrosActuales Los kilómetros totales recorridos por el vehículo hasta el momento.
 */
open class Vehiculo(
    val nombre: String,
    val marca: String,
    val modelo: String,
    val capacidadCombustible: Float,
    var combustibleActual: Float,
    var kilometrosActuales: Float = 0f
) {
    companion object {
        // Define la eficiencia base de combustible en kilómetros por litro para todos los vehículos.
        const val KM_POR_LITRO = 10f
    }

    /**
     * Proporciona información básica sobre la autonomía del vehículo basada en el combustible actual.
     *
     * @return Una cadena de texto describiendo cuántos kilómetros puede recorrer el vehículo con el combustible actual.
     */
    open fun obtenerInformacion(): String = "Con el combustible actual, el vehículo puede recorrer ${calcularAutonomia()} km."

    /**
     * Calcula la autonomía del vehículo basada en la cantidad actual de combustible y la eficiencia base de combustible.
     *
     * @return La autonomía del vehículo en kilómetros.
     */
    open fun calcularAutonomia(): Float = combustibleActual * KM_POR_LITRO

    /**
     * Realiza un viaje consumiendo combustible y aumentando los kilómetros recorridos por el vehículo.
     * Si el viaje excede la autonomía actual, consume todo el combustible disponible.
     *
     * @param distancia La distancia del viaje en kilómetros.
     * @return La distancia restante si el viaje excede la autonomía, 0 si el viaje se completa con éxito.
     */
    open fun realizaViaje(distancia: Float): Float {
        val distanciaMaxima = calcularAutonomia()
        if (distancia <= distanciaMaxima) {
            combustibleActual -= distancia / KM_POR_LITRO
            kilometrosActuales += distancia
            return 0f
        } else {
            combustibleActual = 0f
            kilometrosActuales += distanciaMaxima
            return distancia - distanciaMaxima
        }
    }

    /**
     * Reposta combustible al vehículo. Si no se especifica una cantidad, se llena el tanque completamente.
     *
     * @param cantidad La cantidad de combustible a repostar en litros. Si es 0 o negativa, llena el tanque.
     * @return La cantidad de combustible efectivamente repostada en litros.
     */
    open fun repostar(cantidad: Float = 0f): Float {
        val cantidadRepostada = if (cantidad <= 0f) capacidadCombustible - combustibleActual else minOf(cantidad, capacidadCombustible - combustibleActual)
        combustibleActual += cantidadRepostada
        return cantidadRepostada
    }

    /**
     * Proporciona una representación en cadena de texto del estado actual del vehículo,
     * incluyendo marca, modelo, capacidad de combustible, cantidad de combustible actual y kilómetros recorridos.
     *
     * @return Una cadena de texto que representa el estado actual del vehículo.
     */
    override fun toString(): String {
        return "El vehiculo de marca $marca, modelo $modelo, capacidad de $capacidadCombustible litros y cantidad de $combustibleActual litros, ha recorrido $kilometrosActuales km"
    }
}
