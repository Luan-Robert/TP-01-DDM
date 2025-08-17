package br.unisanta.a4_operacoes_basicas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private lateinit var edt_num1: EditText
    private lateinit var edt_num2: EditText
    private lateinit var btn_calculo: Button // Agora para Soma
    private lateinit var btn_subtracao: Button
    private lateinit var btn_multiplicacao: Button
    private lateinit var btn_divisao: Button
    private lateinit var btn_ohm_v: Button
    private lateinit var btn_ohm_r: Button
    private lateinit var btn_ohm_i: Button
    private lateinit var txv_resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edt_num1 = findViewById(R.id.edt_num1)
        edt_num2 = findViewById(R.id.edt_num2)
        btn_calculo = findViewById(R.id.btn_calculo)
        btn_subtracao = findViewById(R.id.btn_subtracao)
        btn_multiplicacao = findViewById(R.id.btn_multiplicacao)
        btn_divisao = findViewById(R.id.btn_divisao)
        txv_resultado = findViewById(R.id.txv_resultado)

        // Listener para o botão de Soma (btn_calculo)
        btn_calculo.setOnClickListener { performAddition() }

        // Listeners para as operações aritméticas
        btn_subtracao.setOnClickListener { performSubtraction() }
        btn_multiplicacao.setOnClickListener { performMultiplication() }
        btn_divisao.setOnClickListener { performDivision() }

    }

    private fun getNumbers(): Pair<Double, Double>? {
        try {
            val num1 = edt_num1.text.toString().toDouble()
            val num2 = edt_num2.text.toString().toDouble()
            return Pair(num1, num2)
        } catch (e: NumberFormatException) {
            txv_resultado.text = "Erro: Insira valores numéricos válidos."
            return null
        }
    }

    private fun performAddition() {
        val (num1, num2) = getNumbers() ?: return
        val result = num1 + num2
        txv_resultado.text = "Soma: %.2f".format(result)
    }

    private fun performSubtraction() {
        val (num1, num2) = getNumbers() ?: return
        val result = num1 - num2
        txv_resultado.text = "Subtração: %.2f".format(result)
    }

    private fun performMultiplication() {
        val (num1, num2) = getNumbers() ?: return
        val result = num1 * num2
        txv_resultado.text = "Multiplicação: %.2f".format(result)
    }

    private fun performDivision() {
        val (num1, num2) = getNumbers() ?: return
        if (num2 == 0.0) {
            txv_resultado.text = "Erro: Divisão por zero."
            return
        }
        val result = num1 / num2
        txv_resultado.text = "Divisão: %.2f".format(result)
    }
}

