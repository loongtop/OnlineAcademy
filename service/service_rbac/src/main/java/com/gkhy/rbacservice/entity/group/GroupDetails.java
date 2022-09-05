package com.gkhy.rbacservice.entity.group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @ClassName GroupInfo
 * @Description: the information of a group
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
public final class GroupDetails implements Serializable {

    private static final long serialVersionUID = 8931546735898823731L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private Group group;

    private String description;

    private int groupMemberCount;

    private int userMemberCount;

    @OneToMany
    private Set<GroupDetails> parentGroups;
    @OneToMany
    private Set<GroupDetails> childGroups;

}
