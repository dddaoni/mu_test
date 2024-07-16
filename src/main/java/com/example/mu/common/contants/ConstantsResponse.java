package com.example.mu.common.contants;

import com.example.mu.common.errorCd.ErrorCd;
import com.example.mu.common.vo.ReturnVO;

/**
 * 공통 응답 상수 Constants Response
 */
public class ConstantsResponse {
    public static final ReturnVO RES_SUCCESS = new ReturnVO(true,"Request Success!");
    public static final ReturnVO RES_NOT_FOUND = new ReturnVO(ErrorCd.NOT_EXIST_DATA);
    public static final ReturnVO RES_SYSTEM_ERROR = new ReturnVO(ErrorCd.SYSTEM_ERROR);
}
