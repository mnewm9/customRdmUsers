package com.example.genesysanchallenge.model
import com.google.gson.annotations.SerializedName

data class UsersModel(
    @SerializedName("results")
    val resultsList: List<results>?= null,
@SerializedName("info")
val info: Info?=null
)

data class Info(
   val seed: String,
   val results: Int,
   val page: Int,
   val version: String,
)
data class results (
    @SerializedName("gender"                             ) var gender : String?=null,
    @SerializedName("name"                             ) var name: Name?=null,
    @SerializedName("location"                         ) var location: Location? =null,
    @SerializedName("email"                             ) var email : String?=null,
    @SerializedName("picture"                             ) var image : Picture?=null,
    @SerializedName("registered"                             ) var registered : Registered?=null,
    @SerializedName("dob"                                    ) var dob: DOB?= null,
    @SerializedName("phone"                             ) var phone : String?=null,
    @SerializedName("cell"                             ) var cell : String?=null,
    @SerializedName("login"                           ) var login: Login?=null,
    )
data class Name(
    @SerializedName("title")
    var title: String?= null,
    @SerializedName("first")
    var firstName: String?=null,
    @SerializedName("last")
    var lastName: String? = null,
    )

data class Login(
    @SerializedName("username")
    var username: String?= null,
)
data class Location(
    var street: Street?=null,
    @SerializedName("city"                             ) var city : String?=null,
@SerializedName("state"                             ) var state : String?=null,
@SerializedName("country"                             ) var country : String?=null,
@SerializedName("postcode"                             ) var postcode : String?=null,
@SerializedName("coordinates"                             ) var coordinates: Coordinates?=null,
@SerializedName("timezone"                             ) var timezone: Timezone?=null,
)


data class Street(
    var name: String?=null,
    var number: String?=null,
)

data class Picture(
    var thumbnail: String?=null,
    var large: String?=null,
    var medium: String?=null,
)

data class Registered(
    var date:String?=null,
    var age: Int?=null,
)

data class Timezone(
    var offset: String?=null,
    var description : String?= null,
)
data class Coordinates(
    var latitude: String?= null,
    var longitude: String?=null,
)

data class DOB(
    var date : String?=null,
    var age : Int?=null,
)