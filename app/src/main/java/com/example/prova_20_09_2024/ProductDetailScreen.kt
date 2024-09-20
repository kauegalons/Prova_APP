package com.example.prova_20_09_2024

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(productId: Int, navController: NavHostController) {
    val product = Estoque.products[productId]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nome: ${product.name}")
        Text(text = "Categoria: ${product.category}")
        Text(text = "Preço: R$ ${product.price}")
        Text(text = "Quantidade em Estoque: ${product.quantity}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
