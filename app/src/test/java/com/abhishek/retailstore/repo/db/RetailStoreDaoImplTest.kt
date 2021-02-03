package com.abhishek.retailstore.repo.db

import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RetailStoreDaoImplTest {

    private val dao = mockk<IRetailStoreDbDao>(relaxed = true)

    private val product = Product(
        1,
        "Television",
        100.0,
        1
    )

    private val cart = Cart(product)

    @Test
    fun `dao verify insert update `() = runBlockingTest {
        val retailStoreDao = RetailStoreDaoImpl(dao)
        retailStoreDao.insertOrUpdate(cart)

        coVerify { dao.insertOrUpdate(cart) }
    }

    @Test
    fun `dao verify delete cart`() = runBlockingTest {
        val retailStoreDao = RetailStoreDaoImpl(dao)
        retailStoreDao.deleteFromCart(cart)

        coVerify { dao.deleteFromCart(cart) }
    }

    @Test
    fun `dao verify get products from cart`() = runBlockingTest {
        val retailStoreDao = RetailStoreDaoImpl(dao)
        retailStoreDao.getProductsFromCart()

        coVerify { dao.getProductsFromCart() }
    }
}