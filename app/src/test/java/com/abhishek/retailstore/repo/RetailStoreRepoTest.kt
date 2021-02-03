package com.abhishek.retailstore.repo

import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.repo.db.IRetailStoreDao
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RetailStoreRepoTest {

    private val dao = mockk<IRetailStoreDao>(relaxed = true)

    private val product = Product(
        1,
        "Television",
        100.0,
        1
    )

    private val cart = Cart(product)

    @Test
    fun `verify insert update `() = runBlockingTest {
        val repo = RetailStoreRepo(dao)
        repo.insertOrUpdate(cart)

        coVerify { dao.insertOrUpdate(cart) }
    }

    @Test
    fun `verify delete cart from dao`() = runBlockingTest {
        val repo = RetailStoreRepo(dao)
        repo.deleteFromCart(cart)

        coVerify { dao.deleteFromCart(cart) }
    }

    @Test
    fun `verify get products from cart`() = runBlockingTest {
        val repo = RetailStoreRepo(dao)
        repo.getProductsFromCart()

        coVerify { dao.getProductsFromCart() }
    }
}