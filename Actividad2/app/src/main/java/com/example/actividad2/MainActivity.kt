package com.example.actividad2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * @author jose luis garcia zamora
 */

class MainActivity : AppCompatActivity() {
    /**
     *Creamos una variable sin inicializar por cada elemento que tenemos en el layout y tambien de la clase Calculo
     */
    private lateinit var pantalla: TextView
    private lateinit var boton0: Button
    private lateinit var boton1: Button
    private lateinit var boton2: Button
    private lateinit var boton3: Button
    private lateinit var boton4: Button
    private lateinit var boton5: Button
    private lateinit var boton6: Button
    private lateinit var boton7: Button
    private lateinit var boton8: Button
    private lateinit var boton9: Button
    private lateinit var division: Button
    private lateinit var suma: Button
    private lateinit var resta: Button
    private lateinit var multiplicacion: Button
    private lateinit var igual: Button
    private lateinit var reseteo: Button
    private lateinit var cal: Calculo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * cal inicializamos el objeto cal con la clase Calculo
         * e inicializamos cada elemento del layout con sus ids
         */

        cal = Calculo()
        pantalla = findViewById(R.id.pantalla)
        boton0 = findViewById(R.id.num0)
        boton1 = findViewById(R.id.num1)
        boton2 = findViewById(R.id.num2)
        boton3 = findViewById(R.id.num3)
        boton4 = findViewById(R.id.num4)
        boton5 = findViewById(R.id.num5)
        boton6 = findViewById(R.id.num6)
        boton7 = findViewById(R.id.num7)
        boton8 = findViewById(R.id.num8)
        boton9 = findViewById(R.id.num9)
        division = findViewById(R.id.division)
        suma = findViewById(R.id.suma)
        resta = findViewById(R.id.restas)
        multiplicacion = findViewById(R.id.multiplicacion)
        igual = findViewById(R.id.resultado)
        reseteo = findViewById(R.id.resetear)

        /**
         * cada vez que pulsamos cada boton llamamos a la funcion pulsarBoton o Operacion para asginarle los valores a los parametros de la funcion
         * y tambien las funciones igual e reseteo
         */

        boton0.setOnClickListener { pulsarBoton("0") }
        boton1.setOnClickListener { pulsarBoton("1") }
        boton2.setOnClickListener { pulsarBoton("2") }
        boton3.setOnClickListener { pulsarBoton("3") }
        boton4.setOnClickListener { pulsarBoton("4") }
        boton5.setOnClickListener { pulsarBoton("5") }
        boton6.setOnClickListener { pulsarBoton("6") }
        boton7.setOnClickListener { pulsarBoton("7") }
        boton8.setOnClickListener { pulsarBoton("8") }
        boton9.setOnClickListener { pulsarBoton("9") }
        division.setOnClickListener { operacion("/") }
        suma.setOnClickListener { operacion("+") }
        resta.setOnClickListener { operacion("-") }
        multiplicacion.setOnClickListener { operacion("x") }
        igual.setOnClickListener { igual() }
        reseteo.setOnClickListener { reseteo() }
    }

    /**
     * En la funcion pulsarBoton uso la logica de que si no tiene operacion el numero que estoy poniendo se guarda en el num1
     * si no se guardara en el num2
     */

    private fun pulsarBoton(numero: String) {
        if (cal.operacion.isEmpty()) {
            cal.num1 += numero
            pantalla.text = cal.num1
        } else {
            cal.num2 += numero
            pantalla.text = cal.num2
        }
    }

    /**
     * @param signo se le pasa la operacion que quieras hacer por parametro
     * funcion que sirve para cuando el numero 1 no esta vacion y el dos no dices cual es el signo si vuelves a pulsar otro signo se reemplaza ya que num2 sigue vacio
     * si los dos numeros ya estan introducidos y le das igual llama a la funcion resultado para que guarde el resultado y vuelve a dejar num2 vacio
     */

    private fun operacion(signo: String) {
        if (cal.num1.isNotEmpty() && cal.num2.isEmpty()) {
            cal.operacion = signo
        } else if (cal.num1.isNotEmpty() && cal.num2.isNotEmpty()) {
            resultado()
            cal.num1 = cal.resultado.toString()
            cal.num2 = ""
            cal.operacion = signo
        }
    }

    /**
     * Funcion igual para cuando pulsas el boton igual y el num1 y num2 y operacion no esta vacia se hace el resultado y para que despues del igual puedas hacer otra operacion
     * a num2 y operacion se le vuleve a dejar vacios para que se pueda hacer otra operacion y el ese es por si no introduce num 2 o la operacion antes de darle al igual te salga un
     * toast que ter dice que debes de introducir dos numero
     */

    private fun igual() {
        if (cal.num1.isNotEmpty() && cal.num2.isNotEmpty() && cal.operacion.isNotEmpty()) {
            resultado()
            pantalla.text = cal.resultado.toString()
            cal.num1 = cal.resultado.toString()
            cal.num2 = ""
            cal.operacion = ""
        }else{
            val toast = Toast.makeText(this,"Debe introducir 2 números y una operación", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    /**
     * funcion para resetear las variables de la clase Calculo
     */

    private fun reseteo() {
        cal.reset()
        pantalla.text = "0"
    }

    /**
     * Funcion para saber que funcion de la clase Calculo se realiza dependiendo de la operacion que tenga guardada y
     * como num1 y num2 son String lo tienes que pasar a int con toInt()
     */

    private fun resultado() {
        when (cal.operacion) {
            "+" -> cal.suma(cal.num1.toInt(), cal.num2.toInt())
            "-" -> cal.resta(cal.num1.toInt(), cal.num2.toInt())
            "x" -> cal.multiplicacion(cal.num1.toInt(), cal.num2.toInt())
            "/" -> cal.division(cal.num1.toInt(), cal.num2.toInt())
        }
    }
}