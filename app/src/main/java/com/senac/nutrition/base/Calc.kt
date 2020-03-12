package com.senac.nutrition.base

import com.senac.nutrition.Enum.Classification
import com.senac.nutrition.Enum.Genero
import java.io.DataInputStream

// Se tirar o "val" precisa criar o metodo "Init"
class Calc (val peso: Double, val altura: Double, val genero: Genero) {

    fun getImc(): Double {
        return peso / (altura * altura)
    }

    fun getIdealWeight(): Double {
        if (genero == Genero.MASCULINO) {
            return ((altura * 100) - 100) * 0.90
        } else {
            return ((altura * 100) - 100) * 0.85
        }
    }

    fun getClassification(imc : Double): Classification {
        return when {
            imc < 16 -> return Classification.BAIXO_PESO_MUITO_GRAVE
            imc < 16.99 -> return Classification.BAIXO_PESO_GRAVE
            imc < 18.5 -> return Classification.BAIXO_PESO
            imc < 25 -> return Classification.PESO_NORMAL
            imc < 30 -> return Classification.SOBREPESO
            imc < 35 -> return Classification.OBESIDADE_GRAU1
            imc < 40 -> return Classification.OBESIDADE_GRAU2
            else -> return Classification.OBESIDADE_GRAU3
        }
    }
}