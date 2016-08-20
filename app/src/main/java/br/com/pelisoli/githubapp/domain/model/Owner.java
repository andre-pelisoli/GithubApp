package br.com.pelisoli.githubapp.domain.model;

/**
 * Created by pelisoli on 8/16/16.
 */
public class Owner {

    private Integer id;

    private String login;

    private String avatar_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
