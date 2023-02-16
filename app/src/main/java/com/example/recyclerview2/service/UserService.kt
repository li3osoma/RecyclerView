package com.example.recyclerview2.service
import com.example.recyclerview2.model.User
import com.github.javafaker.Faker
import java.util.Collections

typealias UsersListener = (users:List<User>) -> Unit

class UserService {

    private var users:MutableList<User> = mutableListOf<User>()
    private val listeners= mutableSetOf<UsersListener>()

    init{
        val faker=Faker.instance()
        IMAGES.shuffle()
        users = (1..100).map{User(
            id=it.toLong(),
            name=faker.name().name(),
            company =faker.company().name(),
            photo = IMAGES[it% IMAGES.size]
        ) }.toMutableList()
    }

    fun getUsers():List<User>{
        return users
    }

    fun deleteUser(user:User){
        val indexToDelete=users.indexOfFirst { it.id==user.id }
        if(indexToDelete!=-1) {
            users.removeAt(indexToDelete)
        }
        notifyChanges()
    }

    fun moveUser(user:User, moveBy:Int){
        val indexToMove=users.indexOfFirst { it.id==user.id }
        if(indexToMove==-1) return
        val newIndex=indexToMove+moveBy
        if(newIndex<0 || newIndex>users.size) return
        Collections.swap(users,indexToMove,newIndex)
        notifyChanges()
    }

    fun addListener(listener:UsersListener){
        listeners.add(listener)
        listener.invoke(users)

    }

    fun deleteListener(listener: UsersListener){
        listeners.remove(listener)
        listener.invoke(users)
    }

    private fun notifyChanges(){
        listeners.forEach{it.invoke(users)}
    }

    companion object{
        private val IMAGES:MutableList<String> = mutableListOf(
            "https://webpulse.imgsmail.ru/imgpreview?mb=webpulse&key=pulse_cabinet-image-9fe59649-d454-4aee-a389-78198b77ad49",
            "https://cs11.pikabu.ru/post_img/big/2020/08/26/6/1598432456249557793.png",
            "https://media.2x2tv.ru/content/images/2022/04/1611233625_1.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUgAgOCcGVIPrS0VIz-Pg79lvnRpDpQ4t3f5ZQAyUg7wfoWvFALo0Cp_FvMWQMy2CiXsE&usqp=CAU",
            "https://batop.ru/sites/default/files/files/img/personazhy-anime-s-zelenymi-glazami1.jpg",
            "https://www.meme-arsenal.com/memes/ed20da73e8a2ced64cfc761c95748d13.jpg",
            "https://animania-shop.ru/assets/cache/images/!blog/oblozhki/main-qimg-f693b62575b6609c1c863424122e5ff9-600x-1c0.png",
            "http://img-fotki.yandex.ru/get/6415/120613883.b6/0_753ef_9a1b1afe_L.jpg",
            "https://sun9-50.userapi.com/impf/c854016/v854016065/fb2cb/CZlO4wDdAX8.jpg?size=604x547&quality=96&sign=64d1fd78f282c8aa0717eae694d637ec&type=album",
            "https://kg-portal.ru/img/101226/main_2x.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKWHhXQQ2C2cNYv8q_Crw0CT7raSopHWjqvg&usqp=CAU",
            "http://img-fotki.yandex.ru/get/6414/120613883.b6/0_753ec_8207c272_L.jpg",
            "https://avatars.mds.yandex.net/i?id=cd98e2b13f0291d00c4f1e91088833344054f60b-7571104-images-thumbs&n=13",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo0e1Dej93m9plc_CfFQTCBl2rpWgN0bPb5__BHenBvf2AdtQQCgn2fk4DhMuXS83zcUY&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLx7KuetJq770t-PqhFn79f5vXbP2kpu0AnDu0AYluwlJdAnFnWxfcGoDB5GJAN_sa30c&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnwW8lUIvgFY0pmYeS3SdIu9wowQtqitG-MUqYX3hz_TETG2ImpB7GBsevm1Se39Dyb-c&usqp=CAU",
            "https://media.2x2tv.ru/content/images/2022/05/b37966cd0aac8afbf918b14163efc909.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRj3teYNUkd1J_BHqdvVp6GvMtiRzgnjMCVQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcJMbuOvBCHc-U1GHEjWN4mCeyGE9tSVgXkQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6Vexj61rBlPPgHjaoi1J96jDpGTGZU02h3g&usqp=CAU"
        )
    }
}