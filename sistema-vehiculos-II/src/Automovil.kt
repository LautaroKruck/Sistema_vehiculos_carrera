/**
 * Representa un automóvil que participa en la carrera. Extiende la clase Vehiculo para incluir propiedades y comportamientos específicos de los automóviles,
 * tales como si es híbrido y la capacidad de realizar derrapes.
 *
 * @property esHibrido Indica si el automóvil es híbrido (true) o no (false).
 * @param nombre El nombre del vehículo.
 * @param marca La marca del vehículo.
 * @param modelo El modelo del vehículo.
 * @param capacidadCombustible La capacidad total de combustible del vehículo en litros.
 * @param combustibleActual La cantidad actual de combustible en el vehículo en litros.
 * @param kilometrosActuales Los kilómetros totales recorridos por el vehículo.
 */
class Automovil(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val esHibrido: Boolean
) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    companion object {
        // Indica si los automóviles están configurados para la conducción británica (volante a la derecha).
        var condicionBritanica = false

        /**
         * Cambia la configuración global de la condición británica para todos los automóviles.
         *
         * @param nuevaCondicion El nuevo estado de la condición británica (true para activar, false para desactivar).
         */
        fun cambiarCondicionBritanica(nuevaCondicion: Boolean) {
            condicionBritanica = nuevaCondicion
        }
    }

    /**
     * Calcula la autonomía del automóvil basada en su combustible actual y si es híbrido o no.
     * Los automóviles híbridos reciben un bono en la autonomía.
     *
     * @return La autonomía calculada del automóvil en kilómetros.
     */
    override fun calcularAutonomia(): Float {
        val bonusHibrido = if (esHibrido) 5 else 0
        return combustibleActual * (KM_POR_LITRO + bonusHibrido)
    }

    /**
     * Realiza un derrape con el automóvil, lo que resulta en un consumo adicional de combustible.
     * El consumo varía dependiendo de si el automóvil es híbrido o no.
     *
     * @return La cantidad de combustible restante después de realizar el derrape.
     */
    fun realizaDerrape(): Float {
        val gastoDerrape = if (esHibrido) 6.25f else 7.5f
        combustibleActual -= gastoDerrape / KM_POR_LITRO
        return combustibleActual
    }

    /**
     * Proporciona una representación en cadena de texto del estado actual del automóvil, incluyendo marca, modelo, capacidad de combustible,
     * cantidad actual de combustible, si es híbrido y los kilómetros recorridos.
     *
     * @return Una cadena de texto que representa el estado actual del automóvil.
     */
    override fun toString(): String {
        return "El automovil de marca $marca, modelo $modelo, capacidad de $capacidadCombustible litros, cantidad de $combustibleActual litros, hibrido: $esHibrido, ha recorrido $kilometrosActuales km"
    }
}
