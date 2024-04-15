package org.sopt.data.model

import org.sopt.database.entity.SoptEntity
import org.sopt.model.Friend

fun SoptEntity.toFriend() =
    Friend(
        this.id,
        this.name,
        this.hobby,
    )
