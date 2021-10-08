package jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty // 무조건 값이 있도록 하는 값
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore  // 엔티티중에 외부에 노출되지 않도록 하는 선언
    @OneToMany(mappedBy = "member")
    private List<Order> orders =  new ArrayList<>();


}
