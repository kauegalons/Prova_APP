package com.example.prova_20_09_2024

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavigation(navController)
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            ProductRegisterScreen(navController)
        }
        composable("product_list") {
            ProductListScreen(navController)
        }
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailScreen(productId = productId, navController = navController)
        }
        composable("statistics") {
            StatisticsScreen(navController)
        }
    }
}

@Composable
fun ProductRegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nome do Produto") })
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = category, onValueChange = { category = it }, label = { Text("Categoria") })
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = price, onValueChange = { price = it }, label = { Text("Preço") })
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantidade em Estoque") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (name.text.isEmpty() || category.text.isEmpty() || price.text.isEmpty() || quantity.text.isEmpty()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            } else {
                val priceValue = price.text.toDoubleOrNull()
                val quantityValue = quantity.text.toIntOrNull()

                if (priceValue == null || priceValue < 0) {
                    Toast.makeText(context, "Preço deve ser um número maior que zero", Toast.LENGTH_SHORT).show()
                } else if (quantityValue == null || quantityValue < 1) {
                    Toast.makeText(context, "Quantidade deve ser maior que zero", Toast.LENGTH_SHORT).show()
                } else {
                    Estoque.addProduct(Product(name.text, category.text, priceValue, quantityValue))
                    navController.navigate("product_list")
                }
            }
        }) {
            Text("Cadastrar")
        }
    }
}
