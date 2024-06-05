package com.kimbob.pAIk.domain.ingredient.entity;

import com.kimbob.pAIk.domain.common.entity.AbstractEntity;
import com.kimbob.pAIk.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Ingredient extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "double precision")
    private Double volume;

    @Column
    private String volumeUnit;
}
