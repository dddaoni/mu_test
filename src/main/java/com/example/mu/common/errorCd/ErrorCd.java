package com.example.mu.common.errorCd;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCd implements ErrorCdInterface {
    // 400
    NOT_EXIST_DATA(HttpStatus.BAD_REQUEST, "Not exist data"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Bad request"),
    // 500
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    ;

    private HttpStatus status;
    private final String msg;

    @Override
    public String getCd() {
        return this.name();
    }

    /**
     * getMessage
     *  - addMsg O = prefix + addMsg return
     *
     * @param addMsg
     * @return String
     */
    @Override
    public String getMsg(String addMsg) {
        StringBuffer sbf = new StringBuffer(this.msg);
        if (StringUtils.isNotBlank(addMsg)) {
            sbf.append(": ");
            sbf.append(addMsg);
        }
        return sbf.toString();
    }
}
