package acedo.sergio.digimind

import java.io.Serializable

class carrito:Serializable {
    var recordatorios = ArrayList<recordatorio>()

    fun  agregar (p: recordatorio):Boolean{
        return recordatorios.add(p)
    }

}