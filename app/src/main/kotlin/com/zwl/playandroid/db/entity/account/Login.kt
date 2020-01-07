package com.zwl.playandroid.db.entity.account

data class Login(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)
/*
{
    "data": {
        "admin": false,
        "chapterTops": [],
        "collectIds": [
            11333,
            11331
        ],
        "email": "",
        "icon": "",
        "id": 40321,
        "nickname": "zouweilin",
        "password": "",
        "publicName": "zouweilin",
        "token": "",
        "type": 0,
        "username": "zouweilin"
    },
    "errorCode": 0,
    "errorMsg": ""
}
 */