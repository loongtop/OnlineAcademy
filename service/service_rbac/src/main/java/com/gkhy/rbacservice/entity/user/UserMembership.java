package com.gkhy.rbacservice.entity.user;

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
@Entity
public final class UserMembership {

    private static final long serialVersionUID = 8931546735898823731L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String passwordQuestion;

    private String passwordAnswer;

    private Boolean isLockedOut;

    private int loginNumbers;

    private String lastLoginIP;

    private LocalDateTime lastPasswordChangedDate;

    private LocalDateTime lastLockoutDate;

    private LocalDateTime failedPasswordAttemptStartDate;

    private int passwordFailedNumbers;

    private LocalDateTime failedPasswordAnswerAttemptStartDate;

    private int passwordAnswerFailedNumbers;

    private String remark;

}
