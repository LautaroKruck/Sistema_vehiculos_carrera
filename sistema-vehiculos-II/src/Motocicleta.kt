/**
 * Representa una motocicleta que participa en la carrera. Extiende la clase Vehiculo para incluir propiedades y comportamientos específicos de las motocicletas,
 * tales como la cilindrada y la capacidad de realizar caballitos.
 *
 * @property cilindrada La cilindrada de la motocicleta en centímetros cúbicos (cc).
 * @param nombre El nombre del vehículo.
 * @param marca La marca del vehículo.
 * @param modelo El modelo del vehículo.
 * @param capacidadCombustible La capacidad total de combustible del vehículo en litros.
 * @param combustibleActual La cantidad actual de combustible en el vehículo en litros.
 * @param kilometrosActuales Los kilómetros totales recorridos por el vehículo.
 */
class Motocicleta(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val cilindrada: Int
) : Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    companion object {
        // Base de kilómetros por litro para el cálculo de la autonomía de las motocicletas.
        const val KM_POR_LITRO_BASE = 20f
    }

    /**
     * Calcula la autonomía de la motocicleta basada en su combustible actual y su cilindrada.
     * La autonomía se ajusta según la cilindrada de la motocicleta.
     *
     * @return La autonomía calculada de la motocicleta en kilómetros.
     */
    override fun calcularAutonomia(): Float {
        val ajustePorCilindrada = 1f - (cilindrada / 1000f)
        return combustibleActual * (KM_POR_LITRO_BASE + (ajustePorCilindrada * KM_POR_LITRO_BASE))
    }

    /**
     * Realiza un caballito con la motocicleta, lo que resulta en un consumo adicional de combustible.
     *
     * @return La cantidad de combustible restante después de realizar el caballito.
     */
    fun realizaCaballito(): Float {
        val gastoPorCaballito = 6.5f
        combustibleActual -= gastoPorCaballito / KM_POR_LITRO_BASE
        return combustibleActual
    }

    /**
     * Proporciona una representación en cadena de texto del estado actual de la motocicleta, incluyendo marca, modelo, capacidad de combustible,
     * cantidad actual de combustible y cilindrada, así como los kilómetros recorridos.
     *
     * @return Una cadena de texto que representa el estado actual de la motocicleta.
     */
    override fun toString(): String {
        return "La motocicleta de marca $marca, modelo $modelo, capacidad de $capacidadCombustible litros, cantidad de $combustibleActual litros y cilindrada $cilindrada, ha recorrido $kilometrosActuales km"
    }
}
