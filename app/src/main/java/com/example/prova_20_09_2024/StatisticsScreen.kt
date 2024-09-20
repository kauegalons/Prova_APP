package com.example.prova_20_09_2024

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun StatisticsScreen(navController: NavHostController) {
    val totalValue = Estoque.calculateTotalStockValue()
    val totalProducts = Estoque.calculateTotalProducts()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Valor Total do Estoque: R$ $totalValue")
        Text(text = "Quantidade Total de Produtos: $totalProducts unidades")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
