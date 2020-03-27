package wang.ismy.shopa.common;

import lombok.Data;

/**
 * @author MY
 * @date 2020/3/27 20:23
 */
@Data
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {}

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
