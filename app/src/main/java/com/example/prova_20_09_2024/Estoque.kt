package com.example.prova_20_09_2024


object Estoque {
    val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun calculateTotalStockValue(): Double {
        return products.sumOf { it.price * it.quantity }
    }

    fun calculateTotalProducts(): Int {
        return products.sumOf { it.quantity }
    }
}

data class Product(
    val name: String,
    val category: String,
    val price: Double,
    val quantity: Int
)
