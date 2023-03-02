package com.dish.app.dishlist.bl.data

import com.dish.app.dishlist.bl.data.entity.DishEntity

interface DishDataStore{
    suspend fun getDishes(): List<DishEntity>
}