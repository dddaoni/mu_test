package com.example.mu.common.vo;

import com.example.mu.common.errorCd.ErrorCd;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * return vo
 *  - error 인 경우 errorCd 포함
 *  - http status = 개별 정의 필요
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnVO {
    private boolean success;
    private String errorCd;
    private String msg;
    private Object data;

    public ReturnVO(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ReturnVO(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public ReturnVO(ErrorCd errorCd) {
        this.success = false;
        this.errorCd = errorCd.name();
        this.msg = errorCd.getMsg();
    }

    public ReturnVO(ErrorCd errorCd, String msg) {
        this.success = false;
        this.errorCd = errorCd.name();
        this.msg = errorCd.getMsg(msg);
    }
}
