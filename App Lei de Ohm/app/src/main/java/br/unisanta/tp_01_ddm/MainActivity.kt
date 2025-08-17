package br.unisanta.tp_01_ddm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtTextV = findViewById<EditText>(R.id.edtTextV)
        val edtTextR = findViewById<EditText>(R.id.edtTextR)
        val edtTextI = findViewById<EditText>(R.id.edtTextI)
        val txvResult = findViewById<TextView>(R.id.txvResult)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            val vStr = edtTextV.text.toString()
            val rStr = edtTextR.text.toString()
            val iStr = edtTextI.text.toString()


            val v = vStr.toDoubleOrNull()
            val r = rStr.toDoubleOrNull()
            val i = iStr.toDoubleOrNull()

            if ((vStr.isNotBlank() && v == null) ||
                (rStr.isNotBlank() && r == null) ||
                (iStr.isNotBlank() && i == null)) {
                txvResult.text = getString(R.string.erro_entrada_invalida_numerica)
                return@setOnClickListener
            }

            val valoresValidos = listOfNotNull(v, r, i).size

            if (valoresValidos < 2) {
                txvResult.text = getString(R.string.erro_valores_insuficientes)
            } else if (valoresValidos > 2) {
                txvResult.text = getString(R.string.erro_valores_excedentes)
            } else {
                if (v == null && r != null && i != null) {
                    val tensaoCalculada = r * i
                    txvResult.text = getString(R.string.resultado_tensao, tensaoCalculada)
                } else if (r == null && v != null && i != null) {
                    if (i == 0.0) {
                        txvResult.text = getString(R.string.erro_corrente_zero_para_resistencia)
                    } else {
                        val resistenciaCalculada = v / i
                        txvResult.text = getString(R.string.resultado_resistencia, resistenciaCalculada)
                    }
                } else if (i == null && v != null && r != null) {
                    if (r == 0.0) {
                        txvResult.text = getString(R.string.erro_resistencia_zero_para_corrente)
                    } else {
                        val correnteCalculada = v / r
                        txvResult.text = getString(R.string.resultado_corrente, correnteCalculada)
                    }
                } else {
                    txvResult.text = getString(R.string.erro_logica_campos)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

    }
}