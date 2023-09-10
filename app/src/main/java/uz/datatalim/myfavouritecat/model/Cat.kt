package uz.datatalim.myfavouritecat.model

import com.google.gson.annotations.SerializedName

data class Cat(

	@field:SerializedName("Cat")
	val cat: List<CatItem?>? = null
)



data class CatItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("breeds")
	val breeds: List<BreedsItem?>? = null,

	@field:SerializedName("height")
	val height: Int? = null
)


