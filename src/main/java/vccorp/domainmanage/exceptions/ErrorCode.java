package vccorp.domainmanage.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "200", "Thành công"),
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Thất bại"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"501","Không tìm thấy thực thể")
    ;


    private final HttpStatus status;
    private String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }
}
