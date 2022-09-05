package com.gkhy.rbacservice.entity.userDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.rbacservice.entity.UserRbac;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName UserMembership
 * @Description: the information of user(like the Question of password)
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"user", "passwordAnswer"})
@Entity
public final class UserMembership {

    private static final long serialVersionUID = 8931546735898823731L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private UserRbac user;

    @Column(nullable = false)
    private String passwordQuestion = "Who am I ?";

    @Column(nullable = false)
    private String passwordAnswer;

    private Boolean isLockedOut = Boolean.FALSE;

    private int loginNumbers;

    private String lastLoginIP;

    private LocalDateTime lastPasswordChangedDate;

    private LocalDateTime lastLockoutDate;

    private LocalDateTime failedPasswordAttemptStartDate;

    private int passwordFailedNumbers;

    private LocalDateTime failedPasswordAnswerAttemptStartDate;

    private int passwordAnswerFailedNumbers;

    private String description;

}
