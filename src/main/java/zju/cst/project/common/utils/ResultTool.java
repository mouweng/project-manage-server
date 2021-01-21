package zju.cst.project.common.utils;


import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;

/**
 * @Author: wengyifan
 * @Description: JsonResult 结果返回工具
 * @Date Create in 2021/1/21 7:30 下午
 */

public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
