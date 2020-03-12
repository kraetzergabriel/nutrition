package com.senac.nutrition

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrition.R
import com.senac.nutrition.Enum.Genero
import com.senac.nutrition.base.Calc

class MainActivity : AppCompatActivity() {

    private lateinit var edPeso : EditText
    private lateinit var edAltura : EditText
    private lateinit var rdGenero : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edAltura = findViewById<EditText>(R.id.edAltura)
        edPeso = findViewById<EditText>(R.id.edPeso)
        rdGenero = findViewById<RadioGroup>(R.id.rdGrupo)

        var btCalcular = findViewById<Button>(R.id.btCalcular)
        btCalcular.setOnClickListener{
            if (validate()) {
                val genero =
                        if (rdGenero.checkedRadioButtonId == R.id.rbMasculino)
                            Genero.MASCULINO
                        else
                            Genero.FEMININO
                val calc = Calc(
                            edPeso.text.toString().toDouble(),
                            edAltura.text.toString().toDouble(),
                            genero
                )

                val array = resources.getStringArray(R.array.classification)

                val imc = calc.getImc()
                val imcFormatado = String.format("%.2f", imc);

                AlertDialog.Builder(this)
                    .setTitle(R.string.titleConfirmacao)
                    .setMessage(getString(R.string.msgPeso)+imcFormatado)
                    .setPositiveButton(R.string.btOK) {dialog, which ->
                        dialog.dismiss()
                    }.show()
            }
            //"Seu IMC é ${imcFormatado} \n Seu resultado é ${array.get(calc.getClassification(imc).ordinal)}")
        }
    }

    private fun validate() : Boolean {
        var result = true

        if (edPeso.getText().trim().length == 0){
            edPeso.setError(getString(R.string.requiredField))
            result = false
        }

        if(edAltura.getText().trim().length == 0){
            edAltura.setError(getString(R.string.requiredField))
            result = false
        }

        return result
    }
}
