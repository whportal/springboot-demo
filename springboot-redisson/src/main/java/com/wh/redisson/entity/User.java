package com.wh.redisson.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5705953961378110137L;

    private String username;

    private String password;

    private Integer age;

    private Long money;

    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(age, user.age) &&
                Objects.equals(money, user.money) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, age, money, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", birthday=" + birthday +
                '}';
    }
}
