package com.luv2code.demo.demo.modal;

import com.luv2code.demo.demo.enums.ERole;

import javax.persistence.*;

@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private ERole authority;

    public Role() {
    }

    public Role(Account account, ERole authority) {
        this.account = account;
        this.authority = authority;
    }

    public Role(ERole authority) {
        this.authority = authority;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ERole getAuthority() {
        return authority;
    }

    public void setAuthority(ERole authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", account=" + account +
                ", authority=" + authority +
                '}';
    }
}
