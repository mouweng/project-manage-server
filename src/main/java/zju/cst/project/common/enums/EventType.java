package zju.cst.project.common.enums;

/**
 * @author xushifeng
 * @description
 * @date 2021/3/19 9:43 上午
 * 规定
 * # 0 表示创建任务
 * # 1 表示完成任务
 * # 2 表示删除任务
 */
public enum EventType {
    CREATE(0, "创建"),
    FINISH(1, "完成"),
    DELETE(2, "删除"),
    CANCEL_FINISH(3, "取消完成");

    private Integer code;
    private String message;
    EventType(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (EventType ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
