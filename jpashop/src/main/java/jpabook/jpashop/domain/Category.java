package jpabook.jpashop.domain;


import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items =  new ArrayList<>();

    @ManyToOne(fetch = LAZY) //manytoone은 기본설정이 EAGER이다 LAZY(지연로딩)로 설정해야 전체 디비를 끌어오지 않는다
    @JoinColumn(name = "parent_id ")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    //== 연관관계 편의메서드 ==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
