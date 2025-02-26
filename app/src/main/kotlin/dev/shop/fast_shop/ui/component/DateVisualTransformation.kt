package dev.shop.fast_shop.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = formatDate(text.text)

        // Mapeia as posições do cursor entre o texto formatado e o raw (sem formatação)
        val offsetMapping = createOffsetMapping(text.text)

        return TransformedText(
            AnnotatedString(formattedText),
            offsetMapping
        )
    }

    private fun formatDate(raw: String): String {
        return when {
            raw.isEmpty() -> ""
            raw.length <= 2 -> raw // Ex: "2" ou "25"
            raw.length <= 4 -> "${raw.take(2)}/${raw.drop(2)}" // Ex: "25/0" ou "25/02"
            else -> {
                val day = raw.take(2)
                val month = raw.drop(2).take(2)
                val year = raw.drop(4).take(4)
                "$day/$month/$year" // Ex: "25/02/2025"
            }
        }
    }

    private fun createOffsetMapping(raw: String): OffsetMapping {
        return object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Lógica para posicionar o cursor corretamente no texto formatado
                return when {
                    offset <= 2 -> offset // Posições antes da primeira barra
                    offset <= 4 -> offset + 1 // Adiciona 1 devido à primeira barra
                    else -> offset + 2 // Adiciona 2 devido às duas barras
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Lógica inversa para mapear o cursor do formato para o raw
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> offset - 2
                }
            }
        }
    }
}