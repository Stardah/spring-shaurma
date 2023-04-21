package com.kpo.springshaurma.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class ShaurmaUser {

    public enum Role {
        USER;

        public String getName() {
            return this.name();
        }
    }

    public enum AccountType {
        GOOGLE,
        FACEBOOK,
        VK,
        APPLE,
        MANUAL;

        private static final List<AccountType> usedAccountTypes;

        static {
            usedAccountTypes = new ArrayList<>();
            usedAccountTypes.add(GOOGLE);
            usedAccountTypes.add(FACEBOOK);
            usedAccountTypes.add(VK);
            usedAccountTypes.add(APPLE);
            usedAccountTypes.add(MANUAL);
        }

        public static List<AccountType> getUsedAccountTypes() {
            return usedAccountTypes;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ShaurmaUser.AccountType mainAccount;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean ban;

    private LocalDateTime banUntilDate;

    private String pictureUrl;

    private String displayName;

    private Boolean active;

    private String email;

    private String password;

    private Long vkId;

    private String appleId;

    private String googleId;

    private String facebookId;

    @OneToMany(cascade = CascadeType.REMOVE)
    List<RefreshToken> refreshTokens = new LinkedList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
