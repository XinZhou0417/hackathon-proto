package team.gpt2p5.model;

import java.io.Serializable;

public class SysUser implements Serializable {
    private String userId;
    private String name;
    private String email;

    public SysUser(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get a string like "Person@7f54[name=Stephen,age=29,smoker=false]"
     * @return
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "[" +
                "userId=" + userId +
                "name=" + name +
                "email=" + email +
                "]";
    }
}
