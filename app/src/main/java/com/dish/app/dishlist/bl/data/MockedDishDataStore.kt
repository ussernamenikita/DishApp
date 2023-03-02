package com.dish.app.dishlist.bl.data

import com.dish.app.dishlist.bl.data.entity.DishEntity
import kotlinx.coroutines.delay

@Suppress("MaxLineLength", "MagicNumber")
object MockedDishDataStore : DishDataStore {

    private const val REQUEST_DELAY_MS = 3000L

    private val dishes: List<DishEntity> = listOf(
        DishEntity(
            "5ed8da011f071c00465b2026",
            "Бургер \"Америка\"",
            "320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, жареный бекон, сыр чеддер.",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080576786619961414/nbinik_Juicy_burger_in_minimalist_style_b273c023-6925-415e-8c19-45cba0cdbec0.png",
            259,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080592028011864146/nbinik_fast_food_restaurant_logo_in_minimalist_style_with_man_i_bb4f1504-1ce2-4d32-b9ce-03ea99714012.png"
        ),
        DishEntity(
            "5ed8da011f071c00465b2027",
            "Бургер \"Мексика\"",
            "295 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, мексиканские чипсы начос, лист салата, перчики халапеньо, сыр чеддер, соус сальса из свежих томатов.",
            "https://cdn.discordapp.com/attachments/1008571102328541215/1080572334651494420/nbinik_Juicy_burger_in_minimalist_style_1caf9bb1-5310-4225-acfc-ed9b6b193386.png",
            229,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080592028011864146/nbinik_fast_food_restaurant_logo_in_minimalist_style_with_man_i_bb4f1504-1ce2-4d32-b9ce-03ea99714012.png"
        ),
        DishEntity(
            "5ed8da011f071c00465b2028",
            "Бургер \"Русский\"",
            "460 г • Две котлеты из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, маринованные огурчики, двойной сыр чеддер, соус ранч.",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080576824217710772/nbinik_Juicy_burger_in_minimalist_style_c3afd07b-9532-4da0-b246-b3e8709e5f90.png",
            379,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080592028011864146/nbinik_fast_food_restaurant_logo_in_minimalist_style_with_man_i_bb4f1504-1ce2-4d32-b9ce-03ea99714012.png",
        ),
        DishEntity(
            "5ed8da011f071c00465b2029",
            "Бургер \"Люксембург\"",
            "Куриное филе приготовленное на гриле, картофельная булочка на гриле, сыр чеддер, соус ранч, лист салата, томат, свежий огурец.",
            "https://cdn.discordapp.com/attachments/1008571102328541215/1080572308193812491/nbinik_Juicy_burger_in_minimalist_style_50193215-e912-404b-93b0-5256ec5bbd2e.pngg",
            189,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080592028011864146/nbinik_fast_food_restaurant_logo_in_minimalist_style_with_man_i_bb4f1504-1ce2-4d32-b9ce-03ea99714012.png"
        ),
        DishEntity(
            "5ed8da011f071c00465b202a",
            "Бургер \"Классика\"",
            "290 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, сыр чеддер.",
            "https://cdn.discordapp.com/attachments/1008571102328541215/1080572321271664821/nbinik_Juicy_burger_in_minimalist_style_39571b73-6429-4197-935f-d90943883a96.png",
            199,
            null,
        ),
        DishEntity(
            "5ed8da011f071c00465b202b",
            "Бургер \"Швейцария\"",
            "320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, натуральный сырный соус, лист салата, томат, маринованный огурчик, 2 ломтика сыра чеддер.",
            "https://cdn.discordapp.com/attachments/1008571102328541215/1080572633873121370/nbinik_Juicy_burger_in_minimalist_style_4bf59dcb-ca63-42b1-b0d7-bf9f26380617.png",
            279,
            null,
        ),
        DishEntity(
            "5ed8da011f071c00465b202e",
            "Пицца Дьябло с двойной начинкой",
            "Бекон, свинина, пепперони, перец болгарский, халапеньо, томатный пицца-соус, соус шрирача, моцарелла, зелень",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080579545654108160/nbinik_hot_fresh_pizza_minimalist_cartoon_style_0328ffbe-9a46-4f39-a13a-84c327dafb94.png",
            779,
            null,
        ),
        DishEntity(
            "5ed8da011f071c00465b202f",
            "Пицца Карбонара с двойной начинкой",
            "Бекон, пармезан, соус сливочный, моцарелла",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080579824218812496/nbinik_hot_fresh_pizza_minimalist_cartoon_style_4cb2fcc6-9340-4dd9-bfc5-289567a01040.png",
            739,
            null,
        ),
        DishEntity(
            "5ed8da011f071c00465b2030",
            "Пицца Петровская с двойной начинкой",
            "Пепперони, курица, бекон, ветчина, помидоры, шампиньоны, лук красный, моцарелла, укроп",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080579866883272854/nbinik_hot_fresh_pizza_minimalist_cartoon_style_cf0ba0cc-f36c-47f1-bc69-8e0c5d9a6974.png",
            895,
            null
        ),
        DishEntity(
            "5ed8da011f071c00465b2031",
            "Пицца 2 берега с двойной начинкой",
            "Свинина, курица, пепперони, ветчина, бекон, помидоры, перец болгарский, соус барбекю, моцарелла,укроп",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080579959770316840/nbinik_hot_fresh_pizza_minimalist_cartoon_style_bdd33d2f-57ae-494a-86b2-dfe62c5d2056.png",
            899,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080602326533931059/nbinik_restaurant_logo_in_in_acid_style_with_futuristic_car_bd6c8384-9429-49c3-b2fe-fb63225160b8.png"
        ),
        DishEntity(
            "5ed8da011f071c00465b2032",
            "Пицца Мясная с двойной начинкой",
            "Охотничьи колбаски, курица, свинина, пепперони, шампиньоны, томатный пицца-соус, моцарелла, зелень",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080580754590945351/nbinik_hot_fresh_pizza_minimalist_cartoon_style_8445da0f-ac5a-4faa-b209-e74d52756949.png",
            895,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080602326533931059/nbinik_restaurant_logo_in_in_acid_style_with_futuristic_car_bd6c8384-9429-49c3-b2fe-fb63225160b8.png"
        ),
        DishEntity(
            "5ed8da011f071c00465b2033",
            "Пицца Маргарита с двойной начинкой",
            "Моцарелла, помидоры, томатный пицца-соус",
            "https://cdn.discordapp.com/attachments/579743050553622563/1080580527901388873/nbinik_hot_fresh_pizza_minimalist_cartoon_style_d58e27f7-cca9-4e2c-a17f-c1518d07cbe9.png",
            524,
            "https://cdn.discordapp.com/attachments/579743050553622563/1080602326533931059/nbinik_restaurant_logo_in_in_acid_style_with_futuristic_car_bd6c8384-9429-49c3-b2fe-fb63225160b8.png"
        ),
    )

    override suspend fun getDishes(): List<DishEntity> {
        delay(REQUEST_DELAY_MS)
        return dishes
    }
}