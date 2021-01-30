package com.abhishek.retailstore.repo.db

import androidx.room.*
import com.abhishek.retailstore.model.Cart

@Dao
interface IRetailStoreDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: Cart)

//    @Query("UPDATE cart_ SET quantity = quantity+1 and totalPrice = :totalPrice WHERE id = :id")
//    suspend fun updateQuantity(id: Int, totalPrice: Double)

    @Delete
    suspend fun deleteFromCart(cart: Cart)

    @Query("Select * from cart_")
    suspend fun getProductsFromCart(): List<Cart>

    @Query("Select * from cart_ where id= :id")
    suspend fun getProductQuantity(id: Int): Cart

    @Transaction
    suspend fun insertOrUpdate(cart: Cart) {

        val existingCart: Cart? = getProductQuantity(cart.product.id)

        val quantity = existingCart?.quantity ?: 0
        addToCart(
            cart.copy(
                quantity = quantity + 1,
                totalPrice = cart.product.price * (quantity + 1)
            )
        )
    }
}