package io.github.victor_vn.request_scope_bean_poc.feign.dto;

public class UserPlaceholderDto {
    private int userId;
    private int id;
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
